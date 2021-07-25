# 롬복이란?
- 자바에서 Model(DTO, VO, Domain) Object 를 만들때, 멤버필드(프로퍼티)에 대한 Getter/Setter, ToString과 멤버필드에 주입하는 생성자를 만드는 코드 등 불필요하게 반복적으로 만드는 코드를 어노테이션을 통해 줄여 주는 라이브러리
- 사용방법 : Gradle을 사용하는 경우 => build.gradle에 Dependency 추가  `provided 'org.projectlombok:lombok:1.16.20'

### 롬복이 제공하는 어노테이션
- AllArgsConstructor : 모든 매개변수를 사용하여 생성자 추가
- NoArgsConstructor  : 매개변수를 받지 않는 생성자 추가
- RequiredArgsConstructor : 필요한 멤버만 받는 생성자 추가. 필요한 멤버는 @NotNull로 지정
- Getter & Setter : class 선언부 위에 명시
- ToString : 모든 필드를 출력하는 toString() 메소드 생성
- EqualsAndHashCode : hascode 와 equals 메소드를 생성
- Data : 다음 어노테이션을 모두 한번에 처리 한다. ToString + EqualsAndHashCode + Getter(모든 필드) + Setter(모든 필드-final로 성언되지 않은) + RequiredArgsConstructor
- Builder : 다수의 필드를 가지는 복잡한 클래스의 경우, 생성자 대신에 빌더를 사용하면 자동으로 해당 클래스에 빌더를 추가
    - @Singular 어노테이션을 선언해주면 모든 원소를 한 번에 넘기지 않고 원소를 하나씩 추가할 수 있음
  ```
     @Builder
    public class User {
        private Long id;
        private String username;
        @Singular
        private List<Integer> score;
    }
  ```
- NotNull : 변수에 붙이면 자동으로 null 체크를 해준다. 해당 변수가 null로 넘어온 경우, NullPointerException 예외가 발생
- 그 외 : @Cleanup, @Value, @SneakyThrows, @Synchronized, @Getter, @Log 등등

---


- InteliJ Settings - Plugin에서 롬복 추가하기 & build.gradle에도 implementation 추가
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
    implementation 'de.flapdoodle.embed:de.flapdoodle.embed.mongo'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
		// ==============================
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
  
    testCompileOnly 'org.projectlombok:lombok'
    testAnnotationProcessor 'org.projectlombok:lombok'
		// ==============================
    //implementation 'org.projectlombok:lombok'

    implementation 'org.webjars:bootstrap:4.5.0'
    runtimeOnly 'org.springframework.boot:spring-boot-devtools'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```
## @Column 어노테이션
- 기능 : 데이터베이스의 컬럼과 mapping해준다.
### 속성
  - name : 필드와 매핑할 테이블의 컬럼 이름
  - insertable : 엔티티 저장 시 이 필드도 같이 저장. false로 설정하면 데이터베이스에 저장하지 않는다. 읽기전용 일 때 사용
  - updatable : 위와 동일하지만, 엔티티 '수정' 일 때 해당
  - nullable(DDL) : null값 허용 여부를 설정. false일 경우 DDL 생성시 not null제약조건이 된다.
  - unique(DDL) : 한 컬럼에 간단히 유니크 제약 조건을 걸 때 사용. 만약 두 개 이상 걸고 싶다면 클래스 레벨에서 @Table.uniqueConstraints 를 사용해야 함.
  - columnDefinition(DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있다.
  - length : 문자 길이 제약 조건이며, String에만 해당
  - precision, scale(DDL) : BigDecimal 타입에서 사용.(BigInteger가능) precision 은 소수점을 포함한 전체 자리수이고, scale은 소수점 자릿수이다. double과 float 타입에는 적용되지 않는다.

## 발생한 Error
### read API 실행시, type 필드의 기본값이 저장되지 않음
- 해결방법
```
@Column(nullable = true, insertable=false)
private String type;
```


---
### 참고한 글
- https://www.notion.so/jaieve/2-180cc7d4a1a7416d96fb611588b9e2b0#48b6b76e63ae48f396f779ae2d8507a5
- http://wonwoo.ml/index.php/post/717
- https://goddaehee.tistory.com/95
