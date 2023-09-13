# Formats for Commit Message Rules
  타입(Type): 제목(Subject)
  (공백 라인)
  본문(Body)
  (공백 라인)
  꼬리말(Footer)

##  Type      키워드  사용 시점
  - feat      새로운 기능 추가
  - fix       버그 수정
  - docs      문서 수정
  - style     코드 스타일 변경 (코드 포매팅, 세미콜론 누락 등) 기능 수정이 없는 경우
  - design    사용자 UI 디자인 변경 (CSS 등)
  - test      테스트 코드, 리팩토링 테스트 코드 추가
  - refactor  코드 리팩토링
  - build     빌드 파일 수정
  - ci        CI 설정 파일 수정
  - perf      성능 개선
  - chore     빌드 업무 수정, 패키지 매니저 수정 (gitignore 수정 등)
  - rename    파일 혹은 폴더명을 수정만 한 경우
  - remove    파일을 삭제만 한 경우

##  Subject
  - 제목은 50자를 넘기지 않고, 마침표를 붙이지 않습니다.
  - 제목에 커밋 타입을 함께 작성합니다.
  - 과거 시제를 사용하지 않고 명령조로 작성합니다.
  - 제목과 본문은 한 줄 띄워 분리합니다.
  - 제목의 첫 글자는 반드시 대문자로 씁니다.
  - 이슈에 관련된 내용이라면 이슈 번호를 붙힙니다.

## Body
  - 선택 사항이므로 모든 커밋에 작성할 필요는 없습니다.
  - 한 줄에 72자를 넘기면 안 됩니다.
  - 어떻게(how)보다 무엇(what), 왜(why)에 집중하여 내용을 작성합니다.
  - 설명뿐만 아니라 커밋의 이유를 작성할 때도 작성합니다.

## Footer
  - 선택 사항이므로 모든 커밋에 작성할 필요는 없습니다.
  - 이슈를 추적하기 위한 ID를 추가할 때 사용합니다.
    - ( https://github.com/NC7-1st-TeamProject-1st-Team/sns-notification-system/issues 참조)
    - 해결 - 해결한 이슈 ID
    - 관련 - 해당 커밋에 관련된 이슈 ID
    - 참고 - 참고할만한 이슈 ID

# 참고 포스트
https://junhyunny.github.io/information/github/git-commit-message-rule/