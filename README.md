DynamicRecyclerView + MVVM + Hilt + Retrofit2 + Coroutine

<img width="398" alt="image" src="https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/9932864e-ceac-4ca9-929a-f96d83649a08">

<img width="1269" alt="image" src="https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/608dedc5-06c5-40a4-ad6f-a0396d454aec">


<img width="956" alt="image" src="https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/b51a7a76-6e52-4a3b-a639-82faec2ee290">



[구조설명]

1.하나의 MainActivity위에 주간박스오피스리스트를 표출하는 WeeklyBoxOfficeListFragment 와 영화상세정보를 보여주는 WeeklyMovieDetailFragment 두 개를 상황에 따라 보여줍니다.

2.우선 첫 화면에서는 날짜를 입력받고 주간박스오피스 리스트 api를 call하기 위하여 WeeklyBoxOfficeListFragment를 보여줍니다.

3.WeeklyBoxOfficeListFragment를의 viewModel 안에서 coroutine을 이용하여 api를 호출하고 응답받은 xml 데이터를 파싱하여 repository -> usecase -> viewmodel로 전달합니다.

4.viewModel에서는 응답 response를 여러 뷰홀더의 형태로  Recyclerview에서 보여주기 위해 UI Model로 Mapper를 이용하여 맵핑을 합니다.

5.맵핑된 UI Model은 Fragment에서 관찰하여 이 데이터를 Recyclerview에 Add 해주고 UI Model ViewType에 따른 ViewHolder를 ViewHolder Provider를 통해 binding 시켜줍니다.
(이로써 RecyclerView안에 다양한 형태의 ViewHolder를 바인딩할 수 있습니다.)

6.그리고 해당 리스트에서 영화 상세정보를 누르면 UI Model안에 click handler가 있는데 이 handler에 event가 전달되서 movieCd를 WeeklyMovieDetailFragment로 전달합니다.
(savedStateHandle 이용)

7.WeeklyMovieDetailFragment에서는 간단한 TextView 여러개로 영화 상세정보를 보여주기 위해 UI를 단순하게 복수의 TextView로 구현하였습니다.
RecyclerView나 좀 더 많은 데이터를 복잡한 형태로 보여줘야한다면 UI Model을 이용했겠지만 단순한 복수의 텍스트라서 BaseObservable을 이용하여 바인딩 될 수 있도록 하였습니다.

8. 네트워크 모듈과 Dispatcher , useCase , Repository 에서는 Hilt를 이용하여 필요한 클래스를 주입받았습니다.

[아쉬웠던 부분]

1.pageSize와 currentPage가 지원이 되었다면 Paging3를 적용해서 무한 스크롤을 구현하면 좋지 않았을까??

2.diffUtil을 적용하여 성능 최적화를 했으면 어땠을까?
-> api 응답 갯수가 최대 10개로 제한이 되어있어서 생략하였습니다. 

3.모듈화를 적용했으면 좀 더 유지보수에 용이하지 않았을까? 
-> 단순한 기능이고 시간상 생략하였습니다. 

[동작설명]

1. EditTextView에 YYYYMMDD 형태(20120101) 로 입력을 하고 조회를 누릅니다.
   

![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/269443be-355a-4ac7-8cae-a64b06b8d7f9)



3. 해당 날짜에 부합하는 주간 박스오피스 정보 api를 call하고 이에 대한 응답 리스트 데이터가 내려오고 이를 RecyclerView를 통해 표출됩니다.
이때 Recyclerview에는 여러개의 ViewHolder가 동적으로 binding 됩니다.


![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/e64b7ef0-b21b-4229-94a3-97828f97aa27)





![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/be062aa5-ddf9-4a80-9139-ee6d04d4b862)



3.영화 상세정보를 클릭하면 영화 상세정보 api를  call하고 응답 데이터를 또 다른 Fragment에서 보여줍니다.

![image](https://github.com/sorikirisulong/weekly_boxoffice_api_with_dynamic_recyclerview/assets/103094210/70a8fc1c-4d0f-4f07-b4b9-627069d04c83)


