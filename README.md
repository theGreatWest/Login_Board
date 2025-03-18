# [ Mini Project ] 회원 전용 게시판

## 📌 테이블
<table>
    <thead>
        <th>User</th>
        <th>Board</th>
        <th>Post</th>
        <th>Rely</th>
    </thead>
</table>


## 📌 User

`SMS, Email 본인 인증`을 통해 보안 강화<br>
Validation 관련 기능을 `annotation 으로 별도 제작 및 관리`

<table>
    <thead>
        <th>컬럼</th>
        <th>기능</th>
    </thead>
    <tbody>
        <tr>
            <td rowspan="2" style="text-align:center">unique_number</td>
            <td>누적 등록 순서</td>
        </tr>
        <tr><td>id 이외의 고유한 정보</td></tr>
        <tr>
            <td rowspan="2" style="text-align:center">id</td>
            <td>중복 불가능</td>
        </tr>
        <tr><td>길이는 최소 4글자를 만족해야 함</td></tr>
        <tr>
            <td rowspan="3" style="text-align:center">password</td>
            <td>비밀번호</td>
        </tr>
        <tr><td>(!, *, ~, ^) 중 한 글자 이상을 포함해야 함</td></tr>
        <tr><td>길이는 최소 6, 최대 12를 만족해야 함</td></tr>
        <tr>
            <td rowspan="2" style="text-align:center">nickname</td>
            <td>활동명</td>
        </tr>
        <tr><td>중복 불가능</td></tr>
        <tr>
            <td rowspan="3" style="text-align:center">phone1-3</td>
            <td>핸드폰 번호</td>
        </tr>
        <tr><td>(-)를 기준으로 따로 입력 받아 사용할 때 합쳐서 사용함</td></tr>
        <tr><td>아이디/비밀번호 찾기 시, 본인 인증에 이용</td></tr>
        <tr>
            <td rowspan="2" style="text-align:center">email</td>
            <td>이메일</td>
        </tr>
        <tr><td>아이디/비밀번호 찾기 시, 결과 발송</td></tr>
        <tr>
            <td rowspan="3" style="text-align:center">last_login_at</td>
            <td>마지막 로그인 날짜</td>
        </tr>
        <tr><td>계정 생성 및 로그인 시 날짜 저장</td></tr>
        <tr><td>계정 상태 반영 기준</td></tr>
        <tr>
            <td rowspan="4" style="text-align:center">status</td>
            <td>계정 상태</td>
        </tr>
        <tr><td>ACTIVATE: 활성화</td></tr>
        <tr><td>INACTIVATE: 비활성화 - 마지막 로그인 후 1년 간 로그인 기록이 없을 경우 휴면 계정으로 전환. 활성화 상태로 변경하기 위해선 본인 인증 필요</td></tr>
        <tr><td>DELETED: 탈퇴 - 탈퇴 후 3개월 간 보관. 3개월이 지나기 전 로그인 시 계정 복구. 로그인 없을 경우 데이터 삭제 </td></tr>
    </tbody>
</table>

### 기능 설명
`[ 회원 가입 ]`<br>
- **예외 처리**
    - [x] 중복된 아이디 
    - [x] 중복된 닉네임 
    - [x] 비밀번호에 (!, *, ~, ^) 중 한 가지 이상이 포함되지 않은 경우 
    - [x] 비밀번호 크기가 6~12 를 만족하지 않는 경우
    - [x] 핸드폰 번호 앞자리와 중간, 뒷 자리의 길이가 각각 3,4,4를 만족하지 않는 경우
    - [x] 올바르지 않은 이메일 형식

- **기능 목록**
  - [x] 아이디, 닉네임, 핸드폰 번호, 이메일은 입력 단계에서 중복 및 본인 인증 절차 진행
  - [x] 전체 정보(아이디, 비밀번호, 닉네임, 핸드폰 번호, 이메일 상태)를 모두 입력받아 회원 가입

`[ 로그인 ]`
- **기능 목록**
    - [x] 잠금된 계정이라면 비밀번호 재설정 유도
    - [x] 5회 이상 잘못 입력할 경우, 계정 잠금(ACTIVE ➡︎ INACTIVE)
    - [x] 잠금된 계정은 본인 인증 절차 진행 후, 인증 성공 시 비밀번호 재설정

`[ 아이디 / 비밀번호 찾기 ]`
- **기능 목록**
    - [ ] 핸드폰 본인 인증 절차 진행
    - [ ] 인증 성공 시, 전체 아이디/비밀번호 길이의 절반을 가린 상태로 이메일 전송
        <br> [ aaaaaaaaa -> aa***aa ]

`[ 비밀번호 재설정 ]`
- **기능 목록**
    - [x] 핸드폰 또는 이메일 중 하나를 선택해 본인인증 진행
    - [x] 인증 성공 시, 비밀번호 재설정 가능

`[ 계정 상태 ]`
- **기능 목록**
    - [ ] 마지막 회원가입/로그인 후 1년간 로그인 기록이 없을 경우, 휴면 계정으로 전환
    - [ ] 휴면 계정에서 활성화 상태로 돌리기 위해 본인 인증 실시
    - [ ] 탈퇴 시, 3개월간 데이터를 보관 : 3개월 이전에 로그인 시도할 경우 복구 / 없을 경우 데이터 삭제


## 📌 Board

## 📌 Post

## 📌 Reply

