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
<script>
    // Hàm tìm kiếm video
    function searchVideos(event) {
        event.preventDefault();  // Ngừng gửi form

        var keyword = document.getElementById("keyword").value.toLowerCase();  // Lấy từ khóa tìm kiếm và chuyển thành chữ thường

        // Kiểm tra nếu không có từ khóa tìm kiếm
        if (!keyword) {
            alert("Vui lòng nhập từ khóa tìm kiếm");
            return;
        }

        // Lấy tất cả các video trong grid
        var videoCards = document.querySelectorAll(".video-item");

        // Duyệt qua từng video
        videoCards.forEach(function (videoCard) {
            // Lấy tiêu đề video
            var title = videoCard.querySelector("h3").textContent.toLowerCase();

            // Kiểm tra xem tiêu đề video có chứa từ khóa tìm kiếm không
            if (title.includes(keyword)) {
                videoCard.style.display = "block";  // Hiển thị video nếu có kết quả tìm thấy
            } else {
                videoCard.style.display = "none";  // Ẩn video nếu không tìm thấy
            }
        });
    }
</script>
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
            <form id="search-form" onsubmit="searchVideos(event);">
                <input type="text" id="keyword" name="keyword" placeholder="Nhập từ khóa tìm kiếm..." value="${param.keyword}">
            </form>
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
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
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

                    <% if (isAdmin != null && isAdmin) { %>
                    <!-- Các mục chỉ hiển thị cho admin -->
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/listvideo">Nhà Phát triển</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/share">Quản lý chia sẻ</a>
                    </li>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">Đổi mật khẩu</a>
                    </li>
                    <% } %>
                    <li>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
                    </li>
                </ul>
            </div>
            <% } else { %>
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
                        <a href="detail?id=<%= video.getId() %>">
                            <h3><%= video.getTitle() %></h3>
                        </a>
                        <a href="#" class="channel-name"><%=video.getUser().getFullname() %></a> <!-- Tên channel nếu có -->
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
            <p>Mời bạn đăng nhập mới xem được video</p>
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
