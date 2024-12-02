
CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    Password NVARCHAR(50),
    Fullname NVARCHAR(50),
    Email NVARCHAR(100), -- Đã chỉnh lại độ dài của email
    Admin BIT NOT NULL
);

-- Create Video table (Chỉnh lại kiểu dữ liệu Id thành INT nếu là số)
CREATE TABLE Video (
    Id INT PRIMARY KEY not null, -- Đổi kiểu dữ liệu sang INT và sử dụng IDENTITY
    Title NVARCHAR(255) NOT NULL,
    Poster NVARCHAR(255),
    Views INT DEFAULT 0, -- Thêm giá trị mặc định cho Views
    Description NVARCHAR(MAX),
    Active BIT NOT NULL, -- Đảm bảo Active luôn có giá trị
    UserId INT NOT NULL,                       -- ID người dùng đăng video
    CONSTRAINT FK_Video_User FOREIGN KEY (UserId) REFERENCES Users(Id) -- Khóa ngoại liên kết với bảng Users
);

-- Create Favorite table
CREATE TABLE Favorite (
    Id INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    VideoId INT, -- Đảm bảo kiểu dữ liệu VideoId trùng với kiểu của cột Id trong Video table
    LikeDate DATE NOT NULL DEFAULT GETDATE(), -- Thêm giá trị mặc định cho LikeDate
    FOREIGN KEY (UserId) REFERENCES Users(Id),
    FOREIGN KEY (VideoId) REFERENCES Video(Id),
    CONSTRAINT UQ_Favorite_User_Video UNIQUE (UserId, VideoId) -- Đảm bảo mỗi người dùng chỉ thích 1 video 1 lần
);

-- Create Share table
CREATE TABLE Share (
    Id INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    VideoId INT, -- Đảm bảo kiểu dữ liệu VideoId trùng với kiểu của cột Id trong Video table
    Emails NVARCHAR(MAX), -- Danh sách email có thể dài
    ShareDate DATE NOT NULL DEFAULT GETDATE(), -- Thêm giá trị mặc định cho ShareDate
    FOREIGN KEY (UserId) REFERENCES Users(Id),
    FOREIGN KEY (VideoId) REFERENCES Video(Id)
);



-- Create Comments table
CREATE TABLE Comments (
    Id INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    VideoId int,
    CommentText NVARCHAR(MAX),
    CommentDate DATETIME,
    FOREIGN KEY (UserId) REFERENCES Users(Id),
    FOREIGN KEY (VideoId) REFERENCES Video(Id)
);


-- Create Follow table
CREATE TABLE Follow (
    Id INT PRIMARY KEY IDENTITY(1,1),
    FollowerId INT,
    FollowedId INT,
    FollowDate DATETIME,
    FOREIGN KEY (FollowerId) REFERENCES Users(Id),
    FOREIGN KEY (FollowedId) REFERENCES Users(Id)
);

-- Create WatchHistory table
CREATE TABLE WatchHistory (
    Id INT PRIMARY KEY IDENTITY(1,1),
    UserId INT,
    VideoId NVARCHAR(255),
    WatchDate DATETIME,
    FOREIGN KEY (UserId) REFERENCES Users(Id),
    FOREIGN KEY (VideoId) REFERENCES Video(Id)
);

INSERT INTO Users ( Password, Fullname, Email, Admin)
VALUES 
( 'password123', 'Nguyen Van A', 'nguyenvana@example.com', 1),  -- Admin
( 'password456', 'Tran Thi B', 'tranthib@example.com', 0),     -- Người dùng bình thường
( 'password789', 'Le Minh C', 'leminhc@example.com', 0);      

-- Thêm video vào bảng Video
INSERT INTO Video (Id, Title, Poster, Views, Description, Active, UserId)
VALUES 
(1, 'Video 1', 'poster1.jpg', 100, 'Mô tả video 1', 1, 1), -- Ví dụ: Video đầu tiên
(2, 'Video 2', 'poster2.jpg', 200, 'Mô tả video 2', 1, 2), -- Ví dụ: Video thứ hai
(3, 'Video 3', 'poster3.jpg', 150, 'Mô tả video 3', 1, 1), -- Ví dụ: Video thứ ba
(4, 'Video 4', 'poster4.jpg', 300, 'Mô tả video 4', 1, 3), -- Ví dụ: Video thứ tư
(5, 'Video 5', 'poster5.jpg', 250, 'Mô tả video 5', 1, 2); -- Ví dụ: Video thứ năm
-- Thêm video yêu thích vào bảng Favorite
INSERT INTO Favorite (UserId, VideoId, LikeDate)
VALUES 
(1, 'video001', '2024-11-18'),
(2, 'video002', '2024-11-17'),
(3, 'video003', '2024-11-16');

-- Thêm dữ liệu chia sẻ video vào bảng Share
INSERT INTO Share (Id, UserId, VideoId, Emails, ShareDate)
VALUES 
(1, 1, 'video001', 'friend1@example.com, friend2@example.com', '2024-11-18'),
(2, 2, 'video002', 'friend3@example.com', '2024-11-17');


-- Thêm bình luận vào bảng Comments
INSERT INTO Comments (UserId, VideoId, CommentText, CommentDate)
VALUES 
(1, 'video001', 'Bình luận của người dùng 1 trên video 1', '2024-11-18 08:00:00'),
(2, 'video002', 'Bình luận của người dùng 2 trên video 2', '2024-11-17 09:00:00'),
(3, 'video003', 'Bình luận của người dùng 3 trên video 3', '2024-11-16 10:00:00');

-- Thêm mối quan hệ theo dõi vào bảng Follow
INSERT INTO Follow (FollowerId, FollowedId, FollowDate)
VALUES 
(1, 2, '2024-11-18'),
(2, 3, '2024-11-17'),
(3, 1, '2024-11-16');


-- Thêm lịch sử xem vào bảng WatchHistory
INSERT INTO WatchHistory (UserId, VideoId, WatchDate)
VALUES 
(1, 'video001', '2024-11-18 08:00:00'),
(2, 'video002', '2024-11-17 09:00:00'),
(3, 'video003', '2024-11-16 10:00:00');


--Câu Lệnh 

--thêm cột ảnh 

-- Thay đổi kiểu dữ liệu 
ALTER TABLE Users
ALTER COLUMN Password VARCHAR(MAX);


ADD image NVARCHAR(MAX);

ALTER TABLE Video
ADD ShareCount INT DEFAULT 0;

--Truy vấn


SELECT * from Video
SELECT * from Share 
SELECT * from Users
SELECT * from Favorite
SELECT * FROM Video WHERE Title LIKE 'Sample Video 1'