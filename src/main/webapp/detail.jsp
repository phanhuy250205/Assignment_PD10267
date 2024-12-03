<%@ page import="entity.VideoEntity" %>
<%@ page import="entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Chi tiết video - Fun Zone</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
  <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detail.css">

</head>
<body>

<%

  String fullname = (String) session.getAttribute("fullname");

  String message = (String) session.getAttribute("message");
  if (message != null) {
    session.removeAttribute("message"); // Xóa thông báo sau khi hiển thị
%>
<script type="text/javascript">
  alert("<%= message %>"); // Hiển thị thông báo bằng JavaScript
</script>
<% } %>
<!-- Header -->
<header class="main-header">
  <div class="header-left">
    <button class="menu-trigger" id="menu-toggle">
      <i class="bi bi-list"></i>
    </button>
    <a href="${pageContext.request.contextPath}/index" class="brand">
      <i class="bi bi-play-circle"></i>
      <span>FunZone</span>
    </a>
  </div>
  <div class="header-actions">
    <button class="action-btn">
      <i class="bi bi-plus-square"></i>
    </button>
    <button class="action-btn">
      <i class="bi bi-bell"></i>
      <span class="notification-badge">3</span>
    </button>
<%--    Thêm tên--%>
    <a href="/login" class="user-menu">
      <img src="https://picsum.photos/32/32?random=1" alt="User">
      <span><%= fullname%></span>
    </a>
  </div>
</header>

<c:set var="videoId" value="${param.id}" />

<c:if test="${empty videoId}">
  <p>Video không hợp lệ hoặc không tìm thấy.</p>
</c:if>

<c:if test="${not empty videoId}">
  <c:set var="videoDetail" value="${null}" />
  <c:forEach var="video" items="${videos}">
    <c:if test="${video.id == videoId}">
      <c:set var="videoDetail" value="${video}" />
    </c:if>
  </c:forEach>

  <c:if test="${empty videoDetail}">
    <p>Video không hợp lệ hoặc không tìm thấy.</p>
  </c:if>

  <c:if test="${not empty videoDetail}">
    <div class="app-layout">
      <main class="detail-content" style="width: 100%">
        <div class="video-container">
          <div class="video-player">
            <c:if test="${not empty videoDetail.poster}">
              <iframe width="90%" height="100%" src="https://www.youtube.com/embed/${videoDetail.poster}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
            </c:if>
            <c:if test="${empty videoDetail.poster}">
              <p>Video không hợp lệ hoặc không có ID video.</p>
            </c:if>
          </div>
            <div>
              <span class="views">${videoDetail.title} </span>
            </div>
          <div class="video-info">
            <div class="meta-bar">
              <div class="views-date">

                <span class="views">${videoDetail.views} Lượt xem</span>
                <span class="date">2 ngày trước</span>
              </div>

              <div class="action-buttons">

                <c:if test="${not empty sessionScope.userId}">
                  <form action="listvideo?action=addFavorite" method="post">
                    <input type="hidden" name="videoId" value="${videoDetail.id}">
                    <button type="submit" class="btn btn-like" style="color: white ; font-weight: bold">Yêu thích</button>
                  </form>
                </c:if>
                <c:if test="${not empty sessionScope.userId}">
                  <form action="listvideo?action=share" method="post">
                    <!-- Gửi videoId dưới dạng ẩn -->
                    <input type="hidden" name="videoId" value="${videoDetail.id}">
                    <button type="submit" class="btn btn-like" style="color: white ; font-weight: bold">Chia sẻ</button>
                  </form>
                </c:if>
              </div>
            </div>

            <div class="channel-info">
              <div class="channel-main">
                <img src="https://picsum.photos/48/48?random=1" alt="Channel" class="channel-avatar">
                <div class="channel-details">
                  <h3>Music Station</h3>
                  <span>1.2M người đăng ký</span>
                </div>
                <button class="btn btn-primary d-flex align-items-center gap-2">
                  <i class="bi bi-bell"></i>
                  Đăng ký
                </button>
              </div>

              <div class="video-description">
                <p class="description-text">
                    ${videoDetail.description }
                </p>
                <div class="tags">
                  <a href="#" class="badge bg-primary text-decoration-none">#Music</a>
                  <a href="#" class="badge bg-primary text-decoration-none">#Top10</a>
                  <a href="#" class="badge bg-primary text-decoration-none">#Trending</a>
                </div>
              </div>
            </div>

            <!-- Comments Section -->
            <div class="comments-section">
              <div class="comments-header">
                <h3 id="commentCount">125 bình luận</h3>
                <div class="dropdown">
                  <button class="btn btn-light dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    <i class="bi bi-sort-down"></i> Sắp xếp theo
                  </button>
                  <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">Mới nhất</a></li>
                    <li><a class="dropdown-item" href="#">Phổ biến nhất</a></li>
                  </ul>
                </div>
              </div>

              <!-- Form nhập bình luận -->
              <form action="listvideo?action=comment" method="post">
                <div class="comment-form">
                  <img src="https://picsum.photos/40/40?random=2" alt="User" class="user-avatar">
                  <div class="form-floating flex-grow-1">
                    <input type="hidden" name="videoId" value="${videoDetail.id}">
                    <textarea class="form-control" placeholder="Viết bình luận..." id="commentText" name="content"></textarea>
                    <label for="commentText">Viết bình luận...</label>
                  </div>
                  <button type="submit" class="btn btn-primary" id="submitComment">Gửi</button>
                </div>
              </form>
              <div class="comments-section">

                <c:if test="${not empty comments}">
                  <c:forEach var="comment" items="${comments}">
                    <div class="comment" style="display: flex">
                      <div class="img">
                        <img src="https://media.istockphoto.com/id/1737533288/vi/anh/n%E1%BA%B1m-ph%E1%BA%B3ng-v%E1%BB%9Bi-c%C3%A1c-s%E1%BA%A3n-ph%E1%BA%A9m-v%C3%A0-c%C3%B4ng-c%E1%BB%A5-trang-%C4%91i%E1%BB%83m-tr%C3%AAn-n%E1%BB%81n-m%C3%A0u.webp?b=1&s=612x612&w=0&k=20&c=JOgp7oIJK8VtHlqUY-_ddKewTDXM1QCQm4zUb4wsHgQ=" alt="User" class="user-avatar">
                      </div>
                      <div class="content"style="margin-left: 20px;">
                        <strong>${comment.user.fullname}</strong>
                        <p>${comment.commentText}</p>
                        <small>
                          <fmt:formatDate value="${comment.commentDate}" pattern="dd-MM-yyyy HH:mm:ss"/>
                        </small>
                      </div>
                    </div>
                  </c:forEach>
                </c:if>
                <c:if test="${empty comments}">
                  <p>Không có bình luận nào.</p>
                </c:if>
              </div>
            </div>
          </div>
        </div>

        <!-- Related Videos -->
        <div class="related-videos">
          <h3>Video đề xuất</h3>
          <div class="related-list">
            <c:forEach var="video" items="${videos}">
              <div class="related-item">
                <div class="related-thumbnail">
                  <iframe width="90%" height="100%" src="https://www.youtube.com/embed/${video.poster}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
                  <span class="duration">12:45</span>
                </div>
                <div class="related-info">
                  <a href="detail?id=${video.id}">
                    <h4>${video.title}</h4>
                  </a>

                  <span class="channel-name">Music Station</span>
                  <div class="related-meta">
                    <span>${video.views} lượt xem</span>
<%--                    <span>${video.date}</span>--%>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>
        </div>
      </main>
    </div>
  </c:if>
</c:if>


  <script>
    window.onload = function() {
    // Lấy video ID từ URL hiện tại
    const videoId = ${videoDetail.id};

    // Gọi API lấy comments
    fetch('commentServlet?action=listComments&videoId=' + videoId)
    .then(response => response.text())
    .then(html => {
    document.getElementById('commentsContainer').innerHTML = html;
  })
    .catch(error => console.error('Error loading comments:', error));
  }
</script>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>