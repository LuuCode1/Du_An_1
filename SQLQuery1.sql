drop database Ung_Dung_Ban_Kinh
create database Ung_Dung_Ban_Kinh
go
use Ung_Dung_Ban_Kinh
go

-- Lỗi nên sửa sang IDENTITY

CREATE TABLE chat_lieu (
  idChatLieu INT NOT NULL IDENTITY(1,1),
  maChatLieu VARCHAR(50) NOT NULL,
  tenChatLieu NVARCHAR(50) NOT NULL,
  PRIMARY KEY (idChatLieu)
);

CREATE TABLE mau_sac (
  idMauSac INT NOT NULL IDENTITY(1,1),
  maMauSac VARCHAR(50) NOT NULL,
  tenMauSac NVARCHAR(50) NOT NULL,
  PRIMARY KEY (idMauSac)
);

CREATE TABLE thuong_hieu (
  idThuongHieu INT NOT NULL IDENTITY(1,1),
  maThuongHieu VARCHAR(50) NOT NULL,
  tenThuongHieu NVARCHAR(50) NOT NULL,
  PRIMARY KEY (idThuongHieu)
);


-- Thêm tên gọng kính
-- Sửa ảnh về Null
CREATE TABLE gong_kinh (
  idGongKinh INT NOT NULL IDENTITY(1,1),
  maGongKinh  NVARCHAR(50) NOT NULL,
  tenGongKinh  NVARCHAR(50) NOT NULL,
  idChatLieu INT NOT NULL,
  idMauSac INT NOT NULL,
  idThuongHieu INT NOT NULL,
  giaThanh FLOAT NOT NULL,
  soLuong INT NOT NULL,
  hinhanh NVARCHAR(max) NULL,
  moTa NVARCHAR(50) NOT NULL,
  trangThai NVARCHAR(50),
  PRIMARY KEY (idGongKinh),
  FOREIGN KEY (idChatLieu) REFERENCES chat_lieu (idChatLieu),
  FOREIGN KEY (idMauSac) REFERENCES mau_sac (idMauSac),
  FOREIGN KEY (idThuongHieu) REFERENCES thuong_hieu (idThuongHieu),
  
);

-- Thêm tên tròng kính
-- Sửa ảnh về Null
-- Thêm giá thành
CREATE TABLE trong_kinh (
  idTrongKinh INT NOT NULL IDENTITY(1,1),
  maTrongKinh VARCHAR(50) NOT NULL,
  tenTrongKinh  NVARCHAR(50) NOT NULL,
  idChatLieu INT NOT NULL,
  idMauSac INT NOT NULL,
  idThuongHieu INT NOT NULL,
  giaThanh FLOAT NOT NULL,
  doCan FLOAT NOT NULL,
  soLuong INT NOT NULL,
  hinhanh NVARCHAR(max) NULL,
  moTa NVARCHAR(50) NOT NULL,
  trangThai NVARCHAR(50),
  PRIMARY KEY (idTrongKinh),
  FOREIGN KEY (idChatLieu) REFERENCES chat_lieu (idChatLieu),
  FOREIGN KEY (idMauSac) REFERENCES mau_sac (idMauSac),
  FOREIGN KEY (idThuongHieu) REFERENCES thuong_hieu (idThuongHieu),
  
);


-- Chuyển idGongKinh và idTrongKinh từ NOT NULL thành NULL
CREATE TABLE hoa_don_chi_tiet (
  idHoaDonChiTiet  INT NOT NULL IDENTITY(1,1),
  maHoaDonChiTiet  VARCHAR(50) NOT NULL,
  idGongKinh INT  NULL,
  idTrongKinh INT  NULL,
  soluong  INT NOT NULL,
  dongia   FLOAT NOT NULL,
  tonggia  FLOAT NOT NULL,
  PRIMARY KEY (idHoaDonChiTiet),
  FOREIGN KEY (idGongKinh) REFERENCES gong_kinh (idGongKinh),
  FOREIGN KEY (idTrongKinh) REFERENCES trong_kinh (idTrongKinh),
);

CREATE TABLE nhan_vien (
  idNhanVien INT NOT NULL IDENTITY(1,1),
  maNhanVien VARCHAR(50) NOT NULL,
  tenNhanVien  NVARCHAR(50) NOT NULL,
  ngaysinh  DATETIME ,
  sdt   VARCHAR(11)  NOT NULL,
  gioitinh  BIT  NOT NULL,
  email  VARCHAR(30)  NOT NULL,
  trangthai  NVARCHAR(50)  NOT NULL,
  PRIMARY KEY (idNhanVien),
);


CREATE TABLE khach_hang (
  idKhachHang INT NOT NULL IDENTITY(1,1),
  maKhachHang VARCHAR(50) NOT NULL,
  tenKhachHang  NVARCHAR(50) NOT NULL,
  diachi  NVARCHAR(50)  NOT NULL,
  sdt     VARCHAR(11)  NOT NULL,
  PRIMARY KEY (idKhachHang),
);


CREATE TABLE vouchers (
  idVouchers INT NOT NULL IDENTITY(1,1),
  maVouchers VARCHAR(50) NOT NULL,
  tenVouchers  NVARCHAR(50) NOT NULL,
  soluong  INT  NOT NULL,
  ngaytao  DATETIME DEFAULT GETDATE(),
  ngaybatdau  DATETIME,
  ngayketthuc DATETIME,
  trangthai  NVARCHAR(50)  NOT NULL,
  PRIMARY KEY (idVouchers),
);

CREATE TABLE hoa_don (
  idHoaDon  INT NOT NULL IDENTITY(1,1),
  maHoaDon  VARCHAR(50) NOT NULL,
  idVouchers INT NULL ,
  idKhachHang INT NOT NULL ,
  idNhanVien INT NOT NULL ,
  idHoaDonChiTiet  INT NOT NULL ,
  ngayban   DATETIME DEFAULT GETDATE(),
  tongtien  FLOAT NOT NULL,
  trangthai NVARCHAR(50)  NOT NULL, 
  PRIMARY KEY (idHoaDon),
  FOREIGN KEY (idVouchers) REFERENCES vouchers (idVouchers),
  FOREIGN KEY (idKhachHang) REFERENCES khach_hang (idKhachHang),
  FOREIGN KEY (idNhanVien) REFERENCES nhan_vien (idNhanVien),
  FOREIGN KEY (idHoaDonChiTiet) REFERENCES hoa_don_chi_tiet (idHoaDonChiTiet),
);

CREATE TABLE bao_hanh (
  idBaoHanh INT NOT NULL IDENTITY(1,1),
  idHoaDon  INT NOT NULL ,
  maBaoHanh VARCHAR(50) NOT NULL,
  tenBaoHanh  NVARCHAR(50) NOT NULL,
  ngaybaohanh DATETIME,
  ngayketthuc DATETIME,
  thoihan     INT,
  trangthai   NVARCHAR(50)  NOT NULL,
  PRIMARY KEY (idBaoHanh),
  FOREIGN KEY (idHoaDon) REFERENCES hoa_don (idHoaDon)
);

-- INSERT Dữ liệu:
--Bảng chất liệu
INSERT INTO chat_lieu (maChatLieu, tenChatLieu)
VALUES ('CT01', N'Sắt'),
       ('CT02', N'Đồng'),
       ('CT03', N'Kính'),
       ('CT04', N'Nhựa'),
       ('CT05', N'Nhưa dẻo');



--Bảng màu sắc
INSERT INTO mau_sac(maMauSac, tenMauSac)
VALUES ('M01', N'Đỏ'),
       ('M02', N'Hồng'),
       ('M03', N'Trắng'),
       ('M04', N'Đen'),
       ('M05', N'Xanh lục');




--Bảng thương hiệu
INSERT INTO thuong_hieu(maThuongHieu, tenThuongHieu)
VALUES ('TH01', N'Gucci'),
       ('TH02', N'Dior'),
       ('TH03', N'Rayban'),
       ('TH04', N'Gentle Monster'),
       ('TH05', N'Prada');


--Bảng gọng kính 
INSERT INTO gong_kinh(maGongKinh,tenGongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,soLuong,moTa,trangThai)
VALUES ('GK01', N'Gọng Kính V', 2 , 4 , 3 , 333000, 100,N'Sản phẩm thân thiện',N'Đang bán'),
       ('GK02', N'GỌNG KÍNH CẬN CLUB MASTER', 5 , 1 , 2 , 733000,170, N'Sản phẩm thân thiện',N'Đang bán'),
       ('GK03', N'GỌNG KÍNH GỖ NAM CAO CẤP', 2 , 4 , 5 , 883000,201, N'Sản phẩm thân thiện',N'Đang bán'),
       ('GK04', N'GK – 550CN038', 3 , 5 , 4 , 55000,313, N'Sản phẩm thân thiện',N'Đang bán'),
       ('GK05', N'GK – 380CK113', 4 , 3 , 5 , 89000, 298,N'Sản phẩm thân thiện',N'Đang bán');


--Bảng tròng kính 
INSERT INTO trong_kinh(maTrongKinh,tenTrongKinh,idChatLieu,idMauSac,idThuongHieu,giaThanh,doCan,soLuong,moTa,trangThai)
VALUES ('TK01', N'Tròng Kính Chống Ánh Sáng Xanh', 2 , 4 , 3 , 333000, 0,100,N'Sản phẩm thân thiện',N'Đang bán'),
       ('TK02', N'Đa Tròng Essilor Smart-Lens', 5 , 1 , 2 , 733000,0,170, N'Sản phẩm thân thiện',N'Đang bán'),
       ('TK03', N'TRÒNG KÍNH ĐỔI MẦU THÁI LAN TRÁNG', 2 , 4 , 5 , 883000,1.5,201, N'Sản phẩm thân thiện',N'Đang bán'),
       ('TK04', N'TRÒNG KÍNH PHÁP ESSILOR PREVENCIA', 3 , 5 , 4 , 55000,2,313, N'Sản phẩm thân thiện',N'Đang bán'),
       ('TK05', N'TRÒNG KÍNH HÀN QUỐC CHEMI U6 ', 4 , 3 , 5 , 89000,1.75, 298,N'Sản phẩm thân thiện',N'Đang bán');


--Bảng Hóa Đơn Chi Tiết 
INSERT INTO hoa_don_chi_tiet(maHoaDonChiTiet, idGongKinh,idTrongKinh,soluong,dongia,tonggia)
VALUES ('HDCT01', 1,null,2,666000,660000),
       ('HDCT02', 1,2,1,1066000,1060000),
       ('HDCT03', null,5,3,267000,250000),
       ('HDCT04', null,3,1,883000,880000),
       ('HDCT05', 4,null,7,385000,380000);


-- Bảng Nhân viên
INSERT INTO nhan_vien(maNhanVien, tenNhanVien, ngaysinh, sdt, gioitinh, email,trangthai)
VALUES ('NV01',N'Nguyễn Văn A', '4-15-2005', '0365796964', 1 ,'nguyena@gmail.com', N'Đang làm việc'),
       ('NV02',N'Nguyễn Văn B', '7-7-1999',  '0348123364', 1 ,'nguyenb@gmail.com', N'Nghỉ làm'),
       ('NV03',N'Nguyễn Văn C', '4-4-2007',  '0335345964', 0 ,'nguyenc@gmail.com', N'Đang làm việc'),
       ('NV04',N'Nguyễn Văn D', '8-4-1998',  '0348556496', 0 ,'nguyend@gmail.com', N'Đang làm việc'),
       ('NV05',N'Nguyễn Văn E', '7-19-2003', '0348358657', 0 ,'nguyene@gmail.com', N'Đang làm việc');


-- Bảng Vouchers
INSERT INTO vouchers(maVouchers, tenVouchers, soluong, ngaytao, ngaybatdau, ngayketthuc,trangthai)
VALUES ('VC01',N'Voucher 7/7', 1000, '7-1-2023', '7-7-2023' ,'7-14-2023', N'Đang có hiệu lực'),
       ('VC02',N'Voucher Tết', 2000,  '1-20-2023', '2-1-2023' ,'7-8-2023', N'Đang có hiệu lực'),
       ('VC03',N'Voucher Quốc Khánh', 3000,  '8-30-2023', '9-2-2023' ,'7-7-2023', N'Hết hiệu lực'),
       ('VC04',N'Voucher Trung Thu', 1500,  '9-20-2023', '9-29-2023' ,'10-6-2023', N'Hết hiệu lực'),
       ('VC05',N'Voucher 10/10', 1700, '10-1-2023', '10-10-2023' ,'10-17-2023', N'Đang có hiệu lực');


-- Bảng Khách hàng
INSERT INTO khach_hang(maKhachHang, tenKhachHang, diachi, sdt)
VALUES ('KH01',N'Lê Đức A',N'Hà Nội',   '0348596964'),
       ('KH02',N'Lê Đức B',N'Hà Nam',   '0348775757'),
       ('KH03',N'Lê Đức D',N'Thanh Hóa','0348575435'),
       ('KH04',N'Lê Đức C',N'HCM',      '0348597854'),
       ('KH05',N'Lê Đức E',N'Cà Mau',   '0348454864');



-- Bảng Hóa Đơn
INSERT INTO hoa_don(maHoaDon, idVouchers, idKhachHang,idNhanVien,idHoaDonChiTiet,ngayban,tongtien,trangthai)
VALUES ('HD01',null, 1 , 5 , 2 , GETDATE() , 10000000 , N'Đã bán'),
       ('HD02',null, 2, 2 , 3 , GETDATE() , 10876600 , N'Đã bán'),
       ('HD03',4, 3 , 1 , 3 , GETDATE() , 1546000 , N'Đã bán'),
       ('HD04',3, 4 , 4 , 4 , GETDATE() , 18676000 , N'Chưa bán'),
       ('HD05',5, 5 , 3 , 1 , GETDATE() , 1004546460 , N'Chưa bán');


-- Bảng bảo hành
INSERT INTO bao_hanh(maBaoHanh,idHoaDon ,tenBaoHanh, ngaybaohanh, ngayketthuc, thoihan, trangthai)
VALUES ('BH01',1,N'Bảo hành 1', '4-15-2023', '4-15-2024', 8760 , N'Còn hiệu lực'),
       ('BH02',2,N'Bảo hành 22', '7-7-2022', '7-7-2023', 8760 , N'Hết hiệu lực'),
       ('BH03',3,N'Bảo hành 4', '4-4-2022', '4-4-2023', 8760 , N'Hết hiệu lực'),
       ('BH04',4,N'Bảo hành 6', '8-4-2023', '8-4-2024', 8760 , N'Còn hiệu lực'),
       ('BH05',5,N'Bảo hành 9', '7-19-2023', '7-19-2024', 8760 , N'Còn hiệu lực');


SELECT * FROM hoa_don
SELECT * FROM chat_lieu
SELECT * FROM khach_hang
SELECT * FROM vouchers
SELECT * FROM nhan_vien
SELECT * FROM bao_hanh
SELECT * FROM hoa_don_chi_tiet
SELECT * FROM trong_kinh
SELECT * FROM gong_kinh
SELECT * FROM thuong_hieu
SELECT * FROM mau_sac
SELECT    gong_kinh.maGongKinh, gong_kinh.tenGongKinh, chat_lieu.tenChatLieu, mau_sac.tenMauSac, thuong_hieu.tenThuongHieu, gong_kinh.giaThanh, gong_kinh.soLuong, gong_kinh.hinhanh, gong_kinh.moTa
FROM         chat_lieu INNER JOIN
                      gong_kinh ON chat_lieu.idChatLieu = gong_kinh.idChatLieu INNER JOIN
                      mau_sac ON gong_kinh.idMauSac = mau_sac.idMauSac INNER JOIN
                      thuong_hieu ON gong_kinh.idThuongHieu = thuong_hieu.idThuongHieu
					  where chat_lieu.tenChatLieu = ? or  mau_sac.tenMauSac = ? or thuong_hieu.tenThuongHieu = ?   