DynamicRecyclerView + MVVM + Hilt + Retrofit2 + Coroutine

<img width="398" alt="image" src="https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/9932864e-ceac-4ca9-929a-f96d83649a08">

[동작설명]

1. EditTextView에 YYYYMMDD 형태(20120101) 로 입력을 하고 조회를 누릅니다.
   

![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/1589a36c-deb3-4037-948a-b1ac64a991a0)


3. 해당 날짜에 부합하는 주간 박스오피스 정보 api를 call하고 이에 대한 응답 리스트 데이터가 내려오고 이를 RecyclerView를 통해 표출됩니다.
이때 Recyclerview에는 여러개의 ViewHolder가 동적으로 binding 됩니다.


![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/aa1b548b-15bb-47c7-8837-d74e962ca6dd)


![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/49b3d924-161e-46c2-89a3-4fb3362b9de6)


3.영화 상세정보를 클릭하면 영화 상세정보 api를  call하고 응답 데이터를 또 다른 Fragment에서 보여줍니다.

![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/b75e5c75-26c9-4213-b0b9-42a54e4e8fe7)

