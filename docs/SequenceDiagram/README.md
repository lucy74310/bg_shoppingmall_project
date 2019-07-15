### 시퀀스 다이어그램

#### 1. 사용자

##### 회원가입
![](./1.고객-회원가입.PNG)

```
[회원가입 입력 정책]

1. id,password, name, telephone, email, address, birthday 필수입력
2. id : 공백 없이 영문소문자/숫자, 4~16자
3. password : 공백 없이 영문 대소문자/숫자/특수문자 중 3가지 이상 조합, 8자~16자
4. name : 20글자 이하
5. telephone : XXX - XXXX - XXXX 각각 따로 받아서 조합해줄 예정
6. email : @기준으로 따로 받아서 조합해줄 예정
7. address : 우편번호, 주소1, 주소2 따로 받아서 [우편번호] 주소1 주소2 조합해줄 예정
8. birthday : 연,월,일 따로 받아서 yyyy-mm-dd 조합해줄 예정
```



#### 2. 관리자

##### 상품등록
![](./2.관리자-상품등록.PNG)

#### 3.고객&관리자-상품목록&상품조회
![고객&관리자-상품목록&상품조회](https://github.com/lucy74310/bg_shoppingmall_project/blob/master/docs/SequenceDiagram/3.%EA%B3%A0%EA%B0%9D%26%EA%B4%80%EB%A6%AC%EC%9E%90-%EC%83%81%ED%92%88%EB%AA%A9%EB%A1%9D%26%EC%83%81%ED%92%88%EC%A1%B0%ED%9A%8C.PNG)


#### 4. 장바구니 관리
![장바구니 관리](https://github.com/lucy74310/bg_shoppingmall_project/blob/master/docs/SequenceDiagram/4.%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88%20%EA%B4%80%EB%A6%AC.png)


#### 5.주문하기
![주문하기](https://github.com/lucy74310/bg_shoppingmall_project/blob/master/docs/SequenceDiagram/5.%EC%A3%BC%EB%AC%B8.png)



#### 6.주문내역보기
![주문내역보기](https://github.com/lucy74310/bg_shoppingmall_project/blob/master/docs/SequenceDiagram/6.%EC%A3%BC%EB%AC%B8%EB%82%B4%EC%97%AD.png)
