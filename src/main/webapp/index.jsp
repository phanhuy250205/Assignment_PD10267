<%@ page import="entity.VideoEntity" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Fun Zone - Khám phá thế giới video</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

</head>
<body>
<!-- Header -->
<header class="main-header">
    <div class="header-left">
        <button class="menu-trigger" id="menu-toggle">
            <i class="bi bi-list"></i>
        </button>
        <a href="/" class="brand">
            <i class="bi bi-play-circle"></i>
            <span>FunZone</span>
        </a>
    </div>

    <div class="search-box">
        <div class="search-wrapper">
            <i class="bi bi-search search-icon"></i>
            <input type="text" placeholder="Tìm kiếm video..." />
            <button class="voice-search">
                <i class="bi bi-mic"></i>
            </button>
        </div>
    </div>

    <div class="header-actions">
        <button class="action-btn">
            <i class="bi bi-plus-square"></i>
        </button>
        <button class="action-btn">
            <i class="bi bi-bell"></i>
            <span class="notification-badge">3</span>
        </button>
            <%
            String fullname = (String) session.getAttribute("fullname");
            %>
        <div class="user-menu">
            <% if (fullname != null) { %>
            <!-- Khi người dùng đã đăng nhập -->
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
                    Chào, <%= fullname %>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">

                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">Đổi mật khẩu</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/thongtin">Cập nhật thông tin</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/listvideo">Đăng xuất</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Quản Lý Kênh</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/share">Quản Lý chia sẽ</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/thongtin">Thay đổi thông tin</a>
                    </li>
                </ul>
            </div>
            <% }
            else { %>
            <!-- Khi người dùng chưa đăng nhập -->
            <a href="${pageContext.request.contextPath}/login.jsp">
                <img src="https://picsum.photos/32/32?random=1" alt="User" class="user-avatar" />
                <span>Đăng nhập</span>
            </a>
            <% } %>
        </div>

</header>

<div class="app-layout">
    <!-- Menu -->
    <nav class="side-menu" id="side-menu">
        <div class="menu-group">
            <a href="${pageContext.request.contextPath}/index" class="menu-item active">
                <i class="bi bi-house"></i>
                <span>Trang Chủ</span>
            </a>
            <a href="/trending" class="menu-item">
                <i class="bi bi-fire"></i>
                <span>Thịnh hành</span>
            </a>
            <a href="/live" class="menu-item">
                <i class="bi bi-broadcast"></i>
                <span>Trực tiếp</span>
            </a>
        </div>

        <div class="menu-group">
            <h3 class="menu-title">Bộ sưu tập</h3>
            <a href="/history" class="menu-item">
                <i class="bi bi-clock-history"></i>
                <span>Lịch sử xem</span>
            </a>
            <a href="${pageContext.request.contextPath}/listvideo" class="menu-item">
                <i class="bi bi-bookmark"></i>
                <span>Nhà Phát Triển</span>
            </a>
            <a href="${pageContext.request.contextPath}/favorites" class="menu-item">
                <i class="bi bi-heart"></i>
                <span>Đã thích</span>
            </a>
        </div>

        <div class="menu-group">
            <h3 class="menu-title">Kênh của bạn</h3>
            <div class="channel-item">
                <img src="https://picsum.photos/24/24?random=2" alt="Channel" />
                <span>Âm nhạc</span>
                <span class="live-badge">LIVE</span>
            </div>
            <div class="channel-item">
                <img src="https://picsum.photos/24/24?random=3" alt="Channel" />
                <span>Gaming</span>
            </div>
            <div class="channel-item">
                <img src="https://picsum.photos/24/24?random=4" alt="Channel" />
                <span>Tin tức</span>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <main class="main-content">
        <div class="video-grid">

            <%

                // Lấy danh sách video từ request
                List<VideoEntity> videos = (List<entity.VideoEntity>) request.getAttribute("videos");
                if (videos != null && !videos.isEmpty()) {
                    for (VideoEntity video : videos) {
            %>
            <!-- Video Card -->
            <article class="video-item" data-video-id="<%= video.getId() %>">
                <div class="thumbnail-wrapper">
                    <iframe width="300px" height="200px" src="https://www.youtube.com/embed/<%= video.getPoster() %>"
                            frameborder="0"
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                            allowfullscreen></iframe>
                    <span class="duration">15:30</span> <!-- Thời lượng video nếu có -->
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=<%= video.getId() %>"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <a href="detail?id=<%= video.getId()%>">
                            <h3><%= video.getTitle()%></h3>
                        </a>
                        <a href="#" class="channel-name">Phan Huy</a> <!-- Tên channel nếu có -->
                        <div class="meta-data">
                            <span><%= video.getViews() %> lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span> <!-- Thời gian upload nếu cần -->
                        </div>
                    </div>
                </div>
            </article>
            <%
                }
            } else {
            %>
            <p>Không có video nào được kích hoạt.</p>
            <% } %>
        </div>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/src/assets/js/main.js"></script>
</body>
<style>
    .user-menu {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .user-menu img {
        border-radius: 50%;
        width: 32px;
        height: 32px;
    }

    .user-menu span {
        font-weight: bold;
    }
    a{
        text-decoration: none;
    }
</style>
</html>
