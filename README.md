# 학습 관리 시스템(Learning Management System)

## 진행 방법

* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

### 수강신청 요구사항

+ 과정은 여러 개의 강의(session)을 가질 수 있다.
+ 강의는 시작일과 종료일을 가진다.
+ 강의는 강의커버 이미지 정보를 가진다.
+ 강의는 무료 강의, 유료 강의로 나뉜다.
+ 강의 상태는 준비중, 모집중, 종료 3가지 상태가 있다.
+ 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반환된다.

+ [ ] 시작일은 종료일 보다 앞에 있어야 한다.
+ [ ] 수강신청은 모집중일 때만 가능하다.
+ [ ] 이미지 크기는 1MB 이하여야 한다.
+ [ ] 이미지 타입은 gif, jpg(jpeg), png, svg만 허용된다.
+ [ ] 이미지의 크기는 300x200 이상이어야 하며, 비율은 3:2 여야 한다.
+ [ ] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
+ [ ] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
