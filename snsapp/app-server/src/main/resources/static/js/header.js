let notReadNotiCount = document.getElementById("notReadNotiCount").innerText;

const httpRequest = new XMLHttpRequest();

const checkNoti = () => {
  httpRequest.onreadystatechange = () => {
    /* readyState가 Done이고 응답 값이 200일 때 실행 */
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
      if (httpRequest.status === 200) {
        let response = httpRequest.response;
        if(response.notReadNotiCount != notReadNotiCount){
          console.log("새로운 알림!");
          notReadNotiCount = response.notReadNotiCount;
          document.getElementById("notReadNotiCount").innerText = notReadNotiCount;
        } else {
          console.log("새 알림 없음");
        }
      } else {
        alert('Request Error!');
      }
    }
  };
  /* Get 방식으로 name 파라미터와 함께 요청 */
  httpRequest.open('GET', '/notification/notReadNotiCount');
  /* Response Type을 Json으로 사전 정의 */
  httpRequest.responseType = "json";
  /* 정의된 서버에 요청을 전송 */
  httpRequest.send();
}

let intervalId;
setTimeout(checkNoti, 10000, "setTimeout");
setTimeout(intervalId = setInterval(checkNoti, 10000, "setInterval"), 10000, "setTimeout");

