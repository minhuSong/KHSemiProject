<%@ page language="java"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
      crossorigin="anonymous"
    ></script>
    <title>레이아웃</title>
    <style>
      body {
        box-sizing: border-box;
        background-color: rgb(255, 252, 221);
      }

      .header {
        padding: 20px;
        position: relative;
      }

      .footer > * {
        background-color: rgb(255, 216, 131);
        padding: 20px;
        position: relative;
      }

      .ft-ul {
        list-style: none;
        text-align: center;
        height: 100%;
        padding-top: 28px;
        color: black;
        font-size: 12px;
        z-index: 1;
      }
      .ft-images {
        text-align: center;
      }

      #listTable {
        width: 500px;
      }
    </style>
  </head>
  <body>
    <div class="wrapper">
      <div class="row justify-content-center header">
        <div class="col-lg-4 col-12">
          <img
            src="images/project_logo.PNG"
            class="d-block w-100"
            id="main-logo"
          />
        </div>
        <div class="col-lg-8 col-12">
          <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container-fluid">
              <a class="navbar-brand" href="#">Boribob</a>
              <button
                class="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link" href="#">제품 보기</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">고객센터</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">회원 가입</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">리뷰</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">로그인</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">구독하기</a>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
        </div>
      </div>
      <!-- 여기까지 헤더 -->
      <div class="container">
        <div class="col">
          <div class="row">
            <table id="listTable">
              <c:forEach items="${orderList}" var="orderList">
                <thead>
                  <tr>
                    <th>주문번호</th>
                    <th>주문아이디</th>
                    <th>주문자명</th>
                    <th>구독시작일</th>
                    <th>상품명</th>
                    <th>상세내역</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <form action="/detail.order" method="post">
                      <input type="hidden" name="orderId" value="${orderList.orderId}">
                      <td>${orderList.orderId}</td>
                      <td>${orderList.id}</td>
                      <td>${orderList.orderName}</td>
                      <td>${orderList.subscribeStart}</td>
                      <td>${orderList.productCode}</td>
                      <td>
                         
                        <button
                          id="goToDetailBtn"
                          type="submit"
                          class="btn btn-primary"
                        >
                          바로가기
                        </button>
                      </td>
                    </form>
                  </tr>
                </tbody>
              </c:forEach>
            </table>
          </div>

          <button type="button" class="btn btn-primary">
            메인페이지로이동
          </button>
        </div>

        <!-- 여기부터 풋터 -->
        <div class="row justify-content-center footer">
          <div class="col-lg-10 col-12">
            <ul class="ft-ul">
              <li>BoriBob Inc. 사랑시 고백구 행복동</li>
              <li>대표 : 보리밥형제들 사업자등록번호 : 780-86-01094</li>
              <li>
                대표번호 : +82)-665-3430 팩스번호 : +82)-888-3430 홈페이지 :
                petvenience.store.com
              </li>
              <li>CopyrightⓒBoriBob Inc. All Rights Reserved.</li>
            </ul>
          </div>
          <div class="col-lg-2 col-12">
            <ul class="ft-images">
              <img
                src="images/facebook_icon.png"
                style="border-color: lightblue"
              />
              <img
                src="images/instagram_icon.png"
                style="border-color: lightblue"
              />
              <img
                src="images/youtube_icon.png"
                style="border-color: lightblue"
              />
            </ul>
            <ul class="ft-ul">
              <li><strong>고객센터</strong></li>
              <li style="height: 8px"></li>
              <li>오전 10시부터 오후 6시까지</li>
              <li>토요일, 일요일, 공휴일 휴무</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <script></script>
  </body>
</html>
