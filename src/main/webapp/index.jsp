<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 17/11/2024
  Time: 8:46 pm
  To change this template use File | Settings | File Templates.
--%>
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
        <a href="${pageContext.request.contextPath}/login.jsp" class="user-menu">
            <img src="https://picsum.photos/32/32?random=1" alt="User" />
            <span>Đăng nhập</span>
        </a>
    </div>
</header>

<div class="app-layout">
    <!-- Menu -->
    <nav class="side-menu" id="side-menu">
        <div class="menu-group">
            <a href="/" class="menu-item active">
                <i class="bi bi-house"></i>
                <span>Dành cho bạn</span>
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
            <a href="/later" class="menu-item">
                <i class="bi bi-bookmark"></i>
                <span>Xem sau</span>
            </a>
            <a href="/liked" class="menu-item">
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
        <div class="content-header">
            <div class="tags-scroll">
                <button class="tag-btn active">Tất cả</button>
                <button class="tag-btn">Âm nhạc</button>
                <button class="tag-btn">Gaming</button>
                <button class="tag-btn">Tin tức</button>
                <button class="tag-btn">Phim</button>
                <button class="tag-btn">Thể thao</button>
                <button class="tag-btn">Học tập</button>
            </div>
        </div>

        <div class="video-grid">
            <!-- Video Card -->
            <article class="video-item" data-video-id="dQw4w9WgXcQ">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=1" alt="Video" />
                    <span class="duration">15:30</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=1"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Top 10 Ca Khúc Hay Nhất Tháng 3/2024</h3>
                        <a href="#" class="channel-name">Music Station</a>
                        <div class="meta-data">
                            <span>1.2M lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <!-- More Video Cards -->
            <article class="video-item" data-video-id="9bZkp7q19f0">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=2" alt="Video" />
                    <span class="duration">8:45</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=2"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Hướng Dẫn Nấu Món Ngon Cuối Tuần</h3>
                        <a href="#" class="channel-name">Cooking Time</a>
                        <div class="meta-data">
                            <span>856K lượt xem</span>
                            <span class="separator"></span>
                            <span>1 tuần trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="kJQP7kiw5Fk">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=3" alt="Video" />
                    <span class="duration">12:20</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=3"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Review Game Mới Ra Mắt 2024</h3>
                        <a href="#" class="channel-name">Game World</a>
                        <div class="meta-data">
                            <span>2.1M lượt xem</span>
                            <span class="separator"></span>
                            <span>3 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="JGwWNGJdvx8">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=4" alt="Video" />
                    <span class="duration">15:30</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=4"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Shape of You - Official Music Video</h3>
                        <a href="#" class="channel-name">Ed Sheeran</a>
                        <div class="meta-data">
                            <span>1.2M lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="pRpeEdMmmQ0">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=5" alt="Video" />
                    <span class="duration">15:30</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=5"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Hướng dẫn lập trình web cơ bản</h3>
                        <a href="#" class="channel-name">Tech Learning</a>
                        <div class="meta-data">
                            <span>1.2M lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="M7lc1UVf-VE">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=6" alt="Video" />
                    <span class="duration">15:30</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=6"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>JavaScript Tutorial for Beginners</h3>
                        <a href="#" class="channel-name">Code Academy</a>
                        <div class="meta-data">
                            <span>1.2M lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="y8Yv4pnO7qc">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=7" alt="Video" />
                    <span class="duration">15:30</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=7"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Tổng hợp nhạc chill cuối tuần</h3>
                        <a href="#" class="channel-name">Chill Music</a>
                        <div class="meta-data">
                            <span>1.2M lượt xem</span>
                            <span class="separator"></span>
                            <span>2 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>

            <article class="video-item" data-video-id="SlPhMPnQ58k">
                <div class="thumbnail-wrapper">
                    <img src="https://picsum.photos/320/180?random=8" alt="Video" />
                    <span class="duration">25:15</span>
                    <button class="watch-later">
                        <i class="bi bi-clock"></i>
                    </button>
                </div>
                <div class="video-content">
                    <img
                            src="https://picsum.photos/40/40?random=8"
                            alt="Channel"
                            class="channel-img"
                    />
                    <div class="video-info">
                        <h3>Tin Tức Công Nghệ Trong Tuần</h3>
                        <a href="#" class="channel-name">Tech News</a>
                        <div class="meta-data">
                            <span>750K lượt xem</span>
                            <span class="separator"></span>
                            <span>5 ngày trước</span>
                        </div>
                    </div>
                </div>
            </article>
        </div>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="/src/assets/js/main.js"></script>
</body>
</html>
