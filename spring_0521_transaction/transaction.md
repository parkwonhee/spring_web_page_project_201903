
트랜젝션.....
transaction
---------------------------------------------------

논리적 단위로 어떤 한 부분의 작업이 완료되었다 하더라도, 다른 부분의 작업이 완료되지 않을 경우 전체 취소되는 것

이때, 작업이 완료되는 것을 커밋(commit)이라고 하고, 작업이 취소되는 것을 롤백(rollback)
우리 일상생활에 트랜잭션의 예
. 영화 예매를 할 경우 카드 결제 작업과 마일리지 적립 작업은 트랜잭션으로 작동해 야한다./은행 ATM기

프로야구 경기예매-- process
카드결제 ->> 매표소직원(표 발매)
오류의 예) 매표소에서 카드에서 돈이 빠져나갔을 때 일인당 한도 티켓의수로 인해 발권이 안된다면.....크나큰 오류.....



--먼저 테이블 생성
create TABLE card(
consumerid varchar(10),
amount number(4)
)

create TABLE ticket(
consumerid varchar(10),
countnum number(4),
constraint ck_ticket_countnum check (countnum > 5)
)


```
	<!-- jdbc tamplate 설정bean Spring빈을 이용한 코드 간소화-->
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
		<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
		<beans:property name="username" value="scott" />
		<beans:property name="password" value="tiger" />
	</beans:bean>
	
	<beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
	
	<!-- transaction -->
	<beans:bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
	
	<beans:bean name="dao" class="com.javalec.spring_pjt_ex.dao.TicketDao" >
		<beans:property name="template" ref="template" />
		<beans:property name="transactionManager" ref="transactionManager" />
	</beans:bean>
```



dao.class의 틀..

```
//		TransactionDefinition definition = new DefaultTransactionDefinition();
//		TransactionStatus status = transactionManager.getTransaction(definition);
		
//		try {
//		
//			
//		
//			transactionManager.commit(status);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//			transactionManager.rollback(status);
//		}
```	

poem.xml 

```
<!-- JDBC Template -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.1.4.RELEASE</version>
		</dependency>
```



-----------------------------

1. TransactionTemplate

기본적으로 사용한 PlatformTransactionManager 인터페이스 보다 더욱 많이 사용되는 TransactionTemplate에 대해서 학습
많이 사용된 다는 것은 기존의 방법보다 개발자의 수고가 덜 할 수 있다


dao.class--새로 바뀐것

```

		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con)
							throws SQLException {
						String query = "insert into card (consumerId, amount) values (?, ?)";
						PreparedStatement pstmt = con.prepareStatement(query);
						pstmt.setString(1, dto.getConsumerId());
						pstmt.setString(2, dto.getAmount());
						
						return pstmt;
					}
				});
				
				template.update(new PreparedStatementCreator() {
					
					@Override
					public PreparedStatement createPreparedStatement(Connection con)
							throws SQLException {
						String query = "insert into ticket (consumerId, countnum) values (?, ?)";
						PreparedStatement pstmt = con.prepareStatement(query);
						pstmt.setString(1, dto.getConsumerId());
						pstmt.setString(2, dto.getAmount());
						
						return pstmt;
					}
				});
			}
		});

```

app-servlet.xml

```
	<beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
		<beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
		<beans:property name="username" value="scott" />
		<beans:property name="password" value="tiger" />
	</beans:bean>
	
	<beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
	
	<beans:bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>
	
<!-- 이것이 바뀐것....-->

	<beans:bean name="transactionTemplate" class="org.springframework.transaction.support.TransactionTemplate">
		<beans:property name="transactionManager" ref="transactionManager"></beans:property>
	</beans:bean>
	
	<beans:bean name="dao" class="com.javalec.spring_pjt_ex.dao.TicketDao" >
		<beans:property name="template" ref="template" />
		<beans:property name="transactionTemplate" ref="transactionTemplate" />
	</beans:bean>
```

--------------------------------
23-2. 트랜잭션 전파 속성(1)


2개 이상의 트랜잭션이 작동할 때, 기존의 트랜잭션에 참여하는 방법을 결정하는 속성입니다

PROPAGATION_REQUIRED(0)         DEFAULT : 전체 처리

PROPAGATION_REQUIRES_NEW(3)     각각 트랜잭션 처리

PROPAGATION_SUPPORTS(1)         기존 트랜잭션에 의존(밖에 있는것에 의존....)

PROPAGATION_NOT_SUPPORTED(4)    트랜잭션에 포함 하지 않음 – 트랜잭션이 없는 것과 동일 함.

PROPAGATION_MANDATORY(2)        트랜잭션에 꼭 포함 되어야 함. – 트랜잭션이 있는 곳에서 호출해야  됨.

PROPAGATION_NEVER(5)            트랜잭션에 절대 포함 하지 않음. - 트랜잭션이 있는 곳에서 호출하면 에러 발생

```
<beans:bean name="transactionTemplate2" class="org.springframework.transaction.support.TransactionTemplate">
		<beans:property name="transactionManager" ref="transactionManager" />
		<beans:property name="propagationBehavior" value="0"/>
	</beans:bean>
	
	<beans:bean name="dao" class="com.javalec.spring_pjt_ex.dao.TicketDao" >
		<beans:property name="template" ref="template" />
		<beans:property name="transactionTemplate1" ref="transactionTemplate1" />
	</beans:bean>
```
