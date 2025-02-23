

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<!DOCTYPE html>
<html lang="en">Dashboard
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | Video Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style1.css">

</head>

<body class="dashboard-body">
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
        var videoCards = document.querySelectorAll(".video-card");

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
<%
    String fullname = (String) session.getAttribute("fullname");
%>
<div class="dashboard-container">
    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="sidebar-header">
            <img src="https://media.istockphoto.com/id/1979289147/vi/anh/khoa-h%E1%BB%8Dc-ph%C3%A2n-t%C3%ADch-d%E1%BB%AF-li%E1%BB%87u-v%C3%A0-d%E1%BB%AF-li%E1%BB%87u-l%E1%BB%9Bn-v%E1%BB%9Bi-c%C3%B4ng-ngh%E1%BB%87-ai-nh%C3%A0-ph%C3%A2n-t%C3%ADch-ho%E1%BA%B7c-nh%C3%A0-khoa-h%E1%BB%8Dc-s%E1%BB%AD.jpg?s=2048x2048&w=is&k=20&c=kHpQ9kVVIpug7OKpx9rJCdxeL3ZeqfRr02XfT-PlxhE=" alt="Logo" class="logo">
           <a href="${pageContext.request.contextPath}/index" style="text-decoration: none"> <h1>Dashboard</h1></a>
        </div>
        <nav class="sidebar-nav">
            <a href="${pageContext.request.contextPath}/index.jsp" class="nav-link active">
                <i class="fas fa-film"></i>
                <span>Videos</span>
                <span class="badge bg-primary">24</span>
            </a>
            <a href="${pageContext.request.contextPath}/listUsers" class="nav-link">
                <i class="fas fa-users"></i>
                <span>Users</span>
                <span class="badge bg-info">12</span>
            </a>

        </nav>
    </aside>

    <!-- Main Content -->
    <main class="main-content">
        <!-- Top Navigation -->
        <header class="top-nav">
<%--            Hàm tìm kiếm video --%>
            <div class="search-bar">
                <form id="search-form" onsubmit="searchVideos(event);">
                    <input type="text" id="keyword" name="keyword" placeholder="Nhập từ khóa tìm kiếm..." value="${param.keyword}">

                </form>
            </div>
            <div class="top-nav-right">
                <button class="notification-btn">
                    <i class="fas fa-bell"></i>
                    <span class="notification-badge">3</span>
                </button>

                <div class="user-menu">

                    <img src="${pageContext.request.contextPath}/assets/img/${userImage != null && !userImage.isEmpty() ? userImage : 'default-avatar.png'}" alt="Avatar" class="user-avatar" />
                    <span class="user-name"> <%= fullname %></span>
                    <i class="fas fa-chevron-down"></i>
                </div>
            </div>
        </header>

        <!-- Content Area -->
        <div class="content-area">
            <div class="content-header">
                <h2>Video Management</h2>
                <button class="btn-primary" onclick="document.getElementById('addVideoModal').style.display='flex'">
                    <i class="fas fa-plus"></i> Add New Video
                </button>
            </div>

            <!-- Stats Cards -->
            <div class="stats-grid">
                <div class="stat-card">
                    <div class="stat-icon" style="background: rgba(74, 144, 226, 0.1);">
                        <i class="fas fa-film" style="color: #4A90E2;"></i>
                    </div>
                    <div class="stat-info">
                        <h3>Total Videos</h3>
                        <p>${totalVideos}</p>

                    </div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: rgba(16, 185, 129, 0.1);">
                        <i class="fas fa-eye" style="color: #10B981;"></i>
                    </div>
                    <div class="stat-info">
                        <h3>Total Views</h3>
                        <p>${totalViews}</p>

                    </div>
                </div>
                <div class="stat-card">
                    <div class="stat-icon" style="background: rgba(239, 68, 68, 0.1);">
                        <i class="fas fa-heart" style="color: #EF4444;"></i>
                    </div>
                    <div class="stat-info">
                        <h3>Likes</h3>
                        <p>52.1K</p>

                    </div>
                </div>
            </div>

            <!-- Video Grid -->
            <div class="video-grid">
                <c:forEach var="video" items="${videos}">
                    <div class="video-card">
                        <div class="video-thumbnail">
                            <iframe width="385px" height="385px" src="https://www.youtube.com/embed/${video.poster}"
                                    frameborder="0"
                                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                    allowfullscreen></iframe>
                            <span class="duration">12:34</span>

                        </div>
                        <div class="video-info">
                            <h3>${video.title}</h3>
                            <p class="video-stats">
                                <span><i class="fas fa-eye"></i> ${video.views}</span>
                                <span><i class="fas fa-heart"></i>  likes</span>
                            </p>
                            <div class="video-tags">
                                <span class="tag">Tutorial</span>
                                <span class="tag">Beginner</span>
                            </div>

                                <%--  Edit video--%>
                            <div class="video-actions">
                                <a href="listvideo?action=edit&id=${video.id}">
                                    <button class="btn-icon">
                                        <i class="fas fa-pencil"></i>
                                    </button>
                                </a>


                                <form action="listvideo?action=delete&id=${video.id}" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa video này?');">
                                    <button class="btn-icon" type="submit">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </form>
                                <button class="btn-icon"><i class="fas fa-ellipsis-v"></i></button>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </main>
</div>

<!-- Add Video Modal -->
<div id="addVideoModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h3><i class="fas fa-plus-circle"></i> Add New Video</h3>
            <button class="close-btn" onclick="document.getElementById('addVideoModal').style.display='none'">
                <i class="fas fa-times"></i>
            </button>
        </div>
        <div class="modal-body">
            <form class="upload-form" action="listvideo?action=create" method="post">
                <div class="form-group">
                    <label>ID Video</label>
                    <input type="text" placeholder="Enter video ID" name="id">
                </div>
                <div class="form-group">
                    <label>Video Title</label>
                    <input type="text" placeholder="Enter video title" name="title">
                </div>


                <div class="form-group">
                    <label>URL Video</label>
                    <input type="text" placeholder="Enter video Poster" name="poster">
                </div>
                <div class="form-group">
                    <label for="views">Lượt xem:</label>
                    <input type="number" name="views" id="views" value="0" min="0" placeholder="Số lượt xem (mặc định là 0)">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea rows="3" placeholder="Enter video description" name="description"></textarea>
                </div>
                <label for="active">Status</label>
                <select name="active" id="active">
                    <option value="true">Hoạt động</option>
                    <option value="false">Không hoạt động</option>
                </select>
                <div class="modal-footer">
                    <button class="btn-secondary" onclick="document.getElementById('addVideoModal').style.display='none'">Cancel</button>
                    <button class="btn-primary">Upload Video</button>
                    <%--                    <a href="listvideo?action=list">Quay lại danh sách</a>--%>
                </div>
            </form>
        </div>

    </div>
</div>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
