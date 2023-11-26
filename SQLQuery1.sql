﻿drop database Ung_Dung_Ban_Kinh
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

CREATE TABLE loai_sp (
  idloai_sp INT NOT NULL IDENTITY(1,1),
  maloai_sp VARCHAR(50) NOT NULL,
  tenloai_sp NVARCHAR(50) NOT NULL,
  PRIMARY KEY (idloai_sp)
);


CREATE TABLE san_pham(
  idsp          INT NOT NULL IDENTITY(1,1), 
  masp          VARCHAR(50) NOT NULL,
  tensp         NVARCHAR(50) NOT NULL,
  idloai_sp     INT NOT NULL,
  idThuongHieu  INT NOT NULL,
  PRIMARY KEY  (idsp),
  FOREIGN KEY (idloai_sp) REFERENCES loai_sp (idloai_sp),
  FOREIGN KEY (idThuongHieu) REFERENCES thuong_hieu (idThuongHieu), 
)

CREATE TABLE san_pham_chi_tiet(
  id_sp_chi_tiet         INT NOT NULL IDENTITY(1,1), 
  idsp                   INT NULL, 
  idChatLieu             INT NOT NULL,
  idMauSac               INT NOT NULL,
  doCan                  FLOAT NULL,
  giaThanh               FLOAT NOT NULL,
  soLuong                INT NOT NULL,
  hinhanh                NVARCHAR(max) NULL,
  moTa                   NVARCHAR(50) NOT NULL,
  trangThai              NVARCHAR(50),
  PRIMARY KEY (id_sp_chi_tiet),
  FOREIGN KEY (idsp) REFERENCES san_pham (idsp), 
  FOREIGN KEY (idChatLieu) REFERENCES chat_lieu (idChatLieu),
  FOREIGN KEY (idMauSac) REFERENCES mau_sac (idMauSac),
)

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
  maHoaDon  VARCHAR(50)  NULL,
  idVouchers INT NULL ,
  idKhachHang INT NULL ,
  idNhanVien INT  NULL ,
  ngayban   DATETIME DEFAULT GETDATE(),
  tongtien  FLOAT NULL,
  trangthai NVARCHAR(50) NULL, 
  PRIMARY KEY (idHoaDon),
  FOREIGN KEY (idVouchers) REFERENCES vouchers (idVouchers),
  FOREIGN KEY (idKhachHang) REFERENCES khach_hang (idKhachHang),
  FOREIGN KEY (idNhanVien) REFERENCES nhan_vien (idNhanVien),
);


-- Chuyển idGongKinh và idTrongKinh từ NOT NULL thành NULL
CREATE TABLE hoa_don_chi_tiet (
  idHoaDonChiTiet  INT NOT NULL IDENTITY(1,1),
  maHoaDonChiTiet  VARCHAR(50) NOT NULL,
  id_sp_chi_tiet   INT NOT NULL,
  idHoaDon         INT NOT NULL,
  soluong          INT NOT NULL,
  dongia           FLOAT NOT NULL,
  PRIMARY KEY (idHoaDonChiTiet),
  FOREIGN KEY (id_sp_chi_tiet) REFERENCES san_pham_chi_tiet(id_sp_chi_tiet),
  FOREIGN KEY (idHoaDon) REFERENCES hoa_don(idHoaDon),
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

INSERT INTO loai_sp(maloai_sp,tenloai_sp)
VALUES ('L01', N'Mat Kinh'),
       ('L02', N'Trong Kinh'),
       ('L03', N'Gong Kinh');


INSERT INTO san_pham(masp,tensp,idloai_sp,idThuongHieu)
VALUES ('SP01', N'Tròng Kính Chống Ánh Sáng Xanh', 2 , 4 ),
       ('SP02', N'Mat Kinh 2', 1 , 3),
	   ('SP03', N'Gong Kinh 1', 3 , 1),
	   ('SP04', N'Mat Kinh 1', 1 , 2),
	   ('SP05', N'Trong Kinh 1', 2 , 5);


--Bảng tròng kính 
INSERT INTO san_pham_chi_tiet(idsp,idChatLieu,idMauSac,doCan,giaThanh,soLuong,hinhanh,moTa,trangThai)
VALUES (1, 2 , 4 ,0.5,333000,100,null,N'Sản phẩm thân thiện',N'Đang bán'),
       (1, 5 , 1 ,1.5,733000,170, null,N'Sản phẩm thân thiện',N'Đang bán'),
       (1, 2 , 4 ,0.4,883000,201, null,N'Sản phẩm thân thiện',N'Đang bán'),
       (2, 3 , 5 ,1.1,55000,313, null,N'Sản phẩm thân thiện',N'Đang bán'),
       (3, 4 , 3 ,null,89000,298,null,N'Sản phẩm thân thiện',N'Đang bán');


-- Bảng Hóa Đơn
INSERT INTO hoa_don(maHoaDon,idVouchers,idKhachHang,idNhanVien,ngayban,tongtien,trangthai)
VALUES ( null , null , null , null , null , null , N' bán');



--Bảng Hóa Đơn Chi Tiết 
INSERT INTO hoa_don_chi_tiet(maHoaDonChiTiet, idGongKinhCT,idTrongKinhCT,soluong,dongia,tonggia)
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






-- Bảng bảo hành
INSERT INTO bao_hanh(maBaoHanh,idHoaDon ,tenBaoHanh, ngaybaohanh, ngayketthuc, thoihan, trangthai)
VALUES ('BH01',1,N'Bảo hành 1', '4-15-2023', '4-15-2024', 8760 , N'Còn hiệu lực'),
       ('BH02',2,N'Bảo hành 22', '7-7-2022', '7-7-2023', 8760 , N'Hết hiệu lực'),
       ('BH03',3,N'Bảo hành 4', '4-4-2022', '4-4-2023', 8760 , N'Hết hiệu lực'),
       ('BH04',4,N'Bảo hành 6', '8-4-2023', '8-4-2024', 8760 , N'Còn hiệu lực'),
       ('BH05',5,N'Bảo hành 9', '7-19-2023', '7-19-2024', 8760 , N'Còn hiệu lực');

SELECT * FROM loai_sp
SELECT * FROM thuong_hieu
SELECT * FROM mau_sac
SELECT * FROM chat_lieu
SELECT * FROM san_pham
SELECT * FROM san_pham_chi_tiet

SELECT * FROM hoa_don
SELECT * FROM khach_hang
SELECT * FROM vouchers
SELECT * FROM nhan_vien
SELECT * FROM bao_hanh
SELECT * FROM hoa_don_chi_tiet


SELECT idsp,masp,tensp,lsp.idloai_sp,lsp.tenloai_sp,th.idThuongHieu,th.tenThuongHieu
from san_pham sp INNER JOIN
loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN
thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu 

UPDATE san_pham SET masp = ?,tensp = ?,idloai_sp = ?,idThuongHieu = ?
WHERE idsp = ?


SELECT idsp,masp,tensp,lsp.idloai_sp,lsp.tenloai_sp,th.idThuongHieu,th.tenThuongHieu
from san_pham sp INNER JOIN
loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN
thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu 

SELECT sp.idsp,sp.masp,sp.tensp,lsp.tenloai_sp,th.tenThuongHieu
from san_pham sp INNER JOIN
loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp INNER JOIN
thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu
 where sp.masp like 'SP01'


SELECT spct.id_sp_chi_tiet,spct.idChatLieu,spct.idMauSac,spct.doCan,spct.giaThanh,spct.soLuong,spct.hinhanh,spct.moTa,spct.trangThai
from san_pham_chi_tiet spct INNER JOIN 
san_pham sp ON spct.idsp = sp.idsp
where sp.idsp = 1 and (spct.idChatLieu = 2 and spct.idMauSac = 4)



