# [ Mini Project ] 회원 전용 게시판 

## 기능 목록

### User
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

### Board

### Post

### Reply
