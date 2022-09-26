<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/resources/css/total.css">

    </head>
    <body>
        <div>
            <h1>와이파이 정보 구하기</h1>
        </div>

        <%@ include file="/WEB-INF/menubar.jsp" %>

        <div>
            <form class="location-form" id="locationForm" action="/wifiNear.do"
                  method="post">
                <label>LAT:</label>
                <label>
                    <input id="x-coordinate" type="text" name="lat" value="${lat}"
                           placeholder="X좌표"/>
                </label>
                <label>, LNT:</label>
                <label>
                    <input id="y-coordinate" type="text" name="lnt" value="${lnt}"
                           placeholder="Y좌표"/>
                </label>
                <button id="myLocationButton" type="button">내 위치 가져오기</button>
                <button id="getNearBtn">근처 WIFI 정보 보기</button>
            </form>
        </div>
        <div>
            <table id="listTable">
                <thead>
                    <tr>
                        <td>거리(Km)</td>
                        <td>관리번호</td>
                        <td>자치구</td>
                        <td>와이파이명</td>
                        <td>도로명주소</td>
                        <td>상세주소</td>
                        <td>설치위치(층)</td>
                        <td>설치유형</td>
                        <td>설치기관</td>
                        <td>서비스구분</td>
                        <td>망종류</td>
                        <td>설치년도</td>
                        <td>실내외구분</td>
                        <td>WIFI접속환경</td>
                        <td>X좌표</td>
                        <td>Y좌표</td>
                        <td>작업일자</td>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${fn:length(list) > 0}">
                        <c:forEach items="${list}" var="wifi" varStatus="status">
                            <c:if test="${status.index%2 != 0 }"><tr class="listTable-tr-gray"></c:if>
                            <c:if test="${status.index%2 == 0 }"><tr></c:if>
                                <td><c:out value="${wifi.distance}"/></td>
                                <td><c:out value="${wifi.mgrNo}"/></td>
                                <td><c:out value="${wifi.wrdofc}"/></td>
                                <td><c:out value="${wifi.mainNm}"/></td>
                                <td><c:out value="${wifi.adres1}"/></td>
                                <td><c:out value="${wifi.adres2}"/></td>
                                <td><c:out value="${wifi.floor}"/></td>
                                <td><c:out value="${wifi.ty}"/></td>
                                <td><c:out value="${wifi.mby}"/></td>
                                <td><c:out value="${wifi.svcSe}"/></td>
                                <td><c:out value="${wifi.cmcwr}"/></td>
                                <td><c:out value="${wifi.year}"/></td>
                                <td><c:out value="${wifi.door}"/></td>
                                <td><c:out value="${wifi.remars3}"/></td>
                                <td><c:out value="${wifi.lat}"/></td>
                                <td><c:out value="${wifi.lnt}"/></td>
                                <td><c:out value="${wifi.dttm}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <td colspan="17" align="center">위치 정보를 입력한 후에 조회해 주세요.</td>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </body>
</html>

<script type="text/javascript">
    class MainFunction {
        constructor() {
            this.locationForm = document.querySelector(".location-form")
            this.myLocationButton = document.querySelector("#myLocationButton")
        }

        getMyLocation() {
            const myLocationButton = this.myLocationButton;

            myLocationButton.addEventListener("click", () => {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(pos => {
                        document.getElementById("x-coordinate").value = pos.coords.longitude;
                        document.getElementById("y-coordinate").value = pos.coords.latitude;
                    })
                } else {
                    alert('Sorry, no position available.');
                }
            });
        }
    }

    document.addEventListener("DOMContentLoaded", () => {
        new MainFunction().getMyLocation();
    })

    $(".getNearBtn").click(function () {
        var x = parseInt(document.getElementById("x-coordinate").value);
        var y = parseInt(document.getElementById("y-coordinate").value);

        if (!x || !y || x < 0 || y < 0) {
            alert("x, y 좌표 값을 넣어주세요.");
            return false;
        } else {
            document.getElementById("locationForm").submit();
        }
    })
   </script>