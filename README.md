# URL Shortening

- Install & Execute Guide

  - JDK 1.8 Install
    - http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
    
  - Download Guide
    - Download - https://github.com/MisakaMikoto/urlshortening -> download -> zip download
    - extracting zip file
    - cmd or terminal open. move to the extracting file root path (ex: c:/document/urlshortening/)    
    - build : gradlew build (build + jUnit test)    
    - run : gradlew bootRun

  - Clone Guide
    - Checkout url - https://github.com/MisakaMikoto/urlshortening.git
    - cmd or terminal open. move to the checkout path (ex: c:/document/urlshortening/)
    - build : gradlew build (build + jUnit test)    
    - run : gradlew bootRun
    
  - Working Browser
    - Chrome

- Problem Solving

  - Shortening Url
    - 요청 URL 검증 : HttpURLConnection getResponseCode 로 URL 검증. 해당 검증은 ASPECTJ 를 통해 구현 (AOP)
    - 요청 URL 이 같을 경우 : 첫 요청 때, 검증을 통과한 URL 일 경우 H2 데이터 베이스에 저장
                             이 후 요청이 왔을 경우 H2 데이터 베이스에서 해당 URL 을 찾아보고 없으면 저장하고 결과를 반환
                             해당 내용이 H2 데이터 베이스에 있는 경우에는 찾은 결과를 반환
                            
    - 단축 URL 길이 : 4 ~ 8 문자열을 랜덤으로 생성
    - 단축 URL 문자열 타입 : 오직 영문자만 사용했으며, 1 character 당 영문 대소문자를 랜덤으로 생성
    
    - 요청 URL Redirect : 서버에서 redirect 시 CORS 가 발생하기 때문에 서버에서는 Redirect 할 주소 결과만 반환
                          Frontend 에서 반횐된 주소를 받아 redirect
                          
            
