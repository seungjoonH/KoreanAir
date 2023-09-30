# Korean Air

## Description

* 이 프로젝트는 `java swing` 을 활용하여 UI를 대한항공의 항공편 예약 시스템을 모방한 **항공편 통합 관리 시스템**입니다.
* 실사용 목적이 아닌, **디자인 패턴** 학습용 모방 시스템입니다.

## Stack

### Front-end

* `java swing` 을 사용했습니다.

### Database

* DB가 따로 없고, 데이터를 csv파일에 관리합니다.

## Features

* 라이트, 다크, 시스템 테마 모드를 지원합니다.
* 회원가입 및 로그인 할 수 있습니다.
* 관리자와 예약자 두 역할의 사용자가 이용할 수 있습니다.
  * 예약자는 항공편을 조회, 예약, 결제할 수 있습니다.
  * 관리자는 항공편을 조회, 예약, 결제, 추가, 수정, 삭제할 수 있습니다.
* 사용자의 활동 내역이 로그로 남습니다.

## Requirements

이 프로젝트를 실행하기 위해서는 다음이 필요합니다.

* Java compiler

## How to build

1. 이 레포지토리를 `git clone` 합니다.

```
git clone https://github.com/seungjoonH/KoreanAir.git
```

2. `Main` 클래스의 `main()` 을 실행합니다.
  
## Usage

1. Korean Air 프로그램을 설치합니다.
2. 회원가입 후 로그인하여 사용합니다.

## Screenshot

### 1. 메인 (라이트모드)

![light-main](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/light-main.png)

### 2. 메인 (다크모드)

![dark-main](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/dark-main.png)

### 3. 회원가입

![register](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/register.png)

### 4. 항공편조회

![flight-search](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/flight-search.png)

![searched](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/searched.png)

### 5. 항공편예약 및 결제

![flight-reservation](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/flight-reservation.png)

![ticket-payment](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/ticket-payment.png)

### 6. 내 예약

![my-reservation](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/my-reservation.png)

### 7. 내 정보

![my-information](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/my-information.png)

### 8. 활동로그

![activity-log](https://github.com/seungjoonH/KoreanAir/blob/main/asset/screenshot/activity-log.png)
