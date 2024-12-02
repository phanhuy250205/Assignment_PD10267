
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Chia sẻ Video | FunZone</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/shares.css">
</head>
<body>
<div class="app-container">
    <!-- Header -->
    <header class="app-header">
        <div class="header-content">
            <a href="${pageContext.request.contextPath}/index" class="brand">
                <i class="bi bi-play-circle-fill"></i>
                <span>FunZone</span>
            </a>
            <div class="header-actions">
                <button class="btn-notification">
                    <i class="bi bi-bell"></i>
                    <span class="notification-badge">5</span>
                </button>
                <div class="user-menu">
                    <img src="https://picsum.photos/32" alt="User avatar" class="user-avatar">
                    <span>John Doe</span>
                    <i class="bi bi-chevron-down"></i>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <main class="main-content">
        <!-- Stats Overview -->
        <div class="stats-section">
            <div class="stat-card">
                <div class="stat-icon shares">
                    <i class="bi bi-share-fill"></i>
                </div>
                <div class="stat-info">
                    <h3>Tổng lượt chia sẻ</h3>
                    <div class="share-count">
                        <span class="current">156.8K</span>
                        <span class="change positive">+2.4K tuần này</span>
                    </div>
                    <div class="platform-distribution">
                        <div class="platform facebook">
                            <i class="bi bi-facebook"></i>
                            <span>45%</span>
                        </div>
                        <div class="platform twitter">
                            <i class="bi bi-twitter-x"></i>
                            <span>35%</span>
                        </div>
                        <div class="platform other">
                            <i class="bi bi-three-dots"></i>
                            <span>20%</span>
                        </div>
                    </div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-icon engagement">
                    <i class="bi bi-graph-up"></i>
                </div>
                <div class="stat-info">
                    <h3>Tỷ lệ tương tác</h3>
                    <div class="engagement-rate">
                        <span class="current">8.5%</span>
                        <span class="change positive">+1.2% so với tháng trước</span>
                    </div>
                    <div class="engagement-chart">
                        <div class="chart-bar" style="--height: 60%"></div>
                        <div class="chart-bar" style="--height: 75%"></div>
                        <div class="chart-bar" style="--height: 85%"></div>
                        <div class="chart-bar active" style="--height: 100%"></div>
                    </div>
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-icon reach">
                    <i class="bi bi-broadcast"></i>
                </div>
                <div class="stat-info">
                    <h3>Phạm vi tiếp cận</h3>
                    <div class="reach-stats">
                        <div class="reach-item">
                            <span>Trực tiếp</span>
                            <strong>245K</strong>
                        </div>
                        <div class="reach-item">
                            <span>Gián tiếp</span>
                            <strong>789K</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Video Shares Table -->
        <div class="content-section">
            <div class="section-header">
                <h2><i class="bi bi-share"></i> Thống kê chia sẻ video</h2>
                <div class="header-actions">
                    <div class="date-range">
                        <button class="btn-date active">7 ngày</button>
                        <button class="btn-date">30 ngày</button>
                        <button class="btn-date">3 tháng</button>
                        <button class="btn-date custom">
                            <i class="bi bi-calendar3"></i>
                            Tùy chỉnh
                        </button>
                    </div>
                    <button class="btn-export">
                        <i class="bi bi-download"></i>
                        Xuất báo cáo
                    </button>
                </div>
            </div>

            <div class="video-shares-table">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Video</th>
                        <th>Lượt chia sẻ</th>
                        <th>Nền tảng</th>
                        <th>Xu hướng</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="share" items="${sharelist}">
                        <tr>
                            <td>
                                <div class="video-info">
                                    <iframe width="250px" height="250px"
                                            src="https://www.youtube.com/embed/${share.poster}"
                                            frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen>
                                    </iframe>
                                    <div>
                                        <h4>${share.title}</h4>
                                        <span>2 ngày trước • ${share.views} lượt xem</span>
                                    </div>
                                </div>
                            </td>
                            <td>
<%--                                Lượt Chia sẽ video--%>
                                <div class="share-count">
                                    <strong>${share.shareCount}</strong>
                                    <span class="trend positive">+15%</span>
                                </div>
                            </td>
                            <td>
                                <div class="platform-stats">
                                    <div class="platform facebook">
                                        <i class="bi bi-facebook"></i>
                                        <span>8.2K</span>
                                    </div>
                                    <div class="platform twitter">
                                        <i class="bi bi-twitter-x"></i>
                                        <span>3.1K</span>
                                    </div>
                                    <div class="platform other">
                                        <i class="bi bi-three-dots"></i>
                                        <span>1.2K</span>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="trend-chart">
                                    <div class="chart-bar" style="--height: 60%"></div>
                                    <div class="chart-bar" style="--height: 75%"></div>
                                    <div class="chart-bar" style="--height: 85%"></div>
                                    <div class="chart-bar active" style="--height: 100%"></div>
                                </div>
                            </td>
                            <td>
                                <button class="btn-action">
                                    <i class="bi bi-bar-chart"></i>
                                    Chi tiết
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </main>
</div>
<script>

    function shareVideo(videoId) {
        fetch('/share', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'videoId=' + encodeURIComponent(videoId)
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Video shared successfully! New share count: ' + data.newShareCount);
                    // Cập nhật lại giao diện hoặc thực hiện hành động khác nếu cần
                    document.querySelector(`#share-count-${videoId}`).innerText = data.newShareCount;
                } else {
                    alert('Failed to update share count');
                }
            })
            .catch(error => console.error('Error:', error));
    }
</script>
</body>
</html>