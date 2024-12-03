<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25/11/2024
  Time: 12:15 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Yêu Thích | FunZone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/favorites.css">
</head>
<body>
<%
    String fullname = (String) session.getAttribute("fullname");
%>
<!-- Header -->
<header class="app-header">
    <div class="header-content">
        <div class="header-left">
            <a href="${pageContext.request.contextPath}/index" class="brand">
                <i class="fas fa-play-circle"></i>
                <span>FunZone</span>
            </a>
            <div class="search-bar">
                <input type="text" placeholder="Tìm kiếm video...">
                <button><i class="fas fa-search"></i></button>
            </div>
        </div>
        <div class="header-right">

            <div class="user-menu">
                <img src="${pageContext.request.contextPath}/assets/img/${userImage != null && !userImage.isEmpty() ? userImage : 'default-avatar.png'}" alt="Avatar" class="user-avatar" />
                <div class="user-dropdown">
                    <div class="dropdown-header">
                        <img src="${pageContext.request.contextPath}/assets/img/${userImage != null && !userImage.isEmpty() ? userImage : 'default-avatar.png'}" alt="Avatar" id="avatarPreview" />
                        <div class="user-info">
                            <h4><%= fullname%></h4>
                        </div>
                    </div>
                    <div class="dropdown-body">
                        <a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> Đăng xuất</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<main class="main-content">
    <div class="content-header">
        <h1><i class="fas fa-heart"></i> Video Yêu Thích</h1>
        <div class="view-options">
            <button class="active"><i class="fas fa-th-large"></i></button>
            <button><i class="fas fa-list"></i></button>
        </div>
    </div>




    <div class="favorites-grid">
        <c:forEach var="favorite" items="${favorites}">
            <div class="video-card">
                <div class="video-thumbnail">
                    <c:choose>
                        <c:when test="${favorite.video != null && favorite.video.id != null}">
                            <c:if test="${favorite.video.id != null}">
                                <iframe width="550" height="550"
                                        src="https://www.youtube.com/embed/${favorite.video.poster}"
                                        frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                </iframe>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <span class="no-video">Video unavailable</span>
                        </c:otherwise>
                    </c:choose>


                </div>
                <div class="video-info">
                    <div class="channel-info">
                        <img src="https://picsum.photos/32" alt="Channel avatar">
                        <c:if test="${not empty favorite.video.title}">
                            <h3>${favorite.video.title}</h3>
                        </c:if>
                    </div>
                    <div class="channel-name">${favorite.user.fullname}</div>
                    <div class="video-stats">
                        <span><i class="fas fa-eye"></i> ${favorite.video.views}</span>
                        <span><i class="fas fa-clock"></i> CHào bạn</span>
                    </div>
                    <div class="video-actions">
                        <form action="favorites" method="post">
                            <input type="hidden" name="id" value="${favorite.id}">  <!-- ID của yêu thích -->
                            <input type="hidden" name="action" value="delete">  <!-- Hành động xóa yêu thích -->
                            <button type="submit" class="btn-unlike">
                                <i class="fas fa-heart-broken"></i>  <!-- Biểu tượng trái tim -->
                                <span>Unfavorite</span>  <!-- Văn bản "Unfavorite" -->
                            </button>
                        </form>
                        <button class="btn-share">
                            <i class="fas fa-share"></i>
                            <span>Share</span>
                        </button>
                        <button class="btn-more">
                            <i class="fas fa-ellipsis-v"></i>
                        </button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>







    <!-- Empty State (hidden by default) -->
    <div class="empty-state" style="display: none;">
        <div class="empty-content">
            <i class="fas fa-heart"></i>
            <h2>Chưa có video yêu thích</h2>
            <p>Hãy thêm video yêu thích để xem lại sau!</p>
            <button class="btn-primary">
                <i class="fas fa-compass"></i>
                Khám phá video
            </button>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>