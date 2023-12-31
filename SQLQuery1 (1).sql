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
  giaNhap				 FLOAT NOT NULL,
  soLuong                INT NOT NULL,
  hinhanh                NVARCHAR(max) NULL,
  moTa                   NVARCHAR(50) NOT NULL,
  trangThai              NVARCHAR(50),
  PRIMARY KEY (id_sp_chi_tiet),
  FOREIGN KEY (idsp) REFERENCES san_pham (idsp), 
  FOREIGN KEY (idChatLieu) REFERENCES chat_lieu (idChatLieu),
  FOREIGN KEY (idMauSac) REFERENCES mau_sac (idMauSac),
)

CREATE TABLE Nguoi_dung (
  idnguoi_dung INT NOT NULL IDENTITY(1,1),
  maNguoiDung VARCHAR(100) NOT NULL,
  tenNguoi_dung  NVARCHAR(50) NOT NULL,
  ngaysinh  DATE,
  matKhau VARCHAR(18) NOT NULL,
  sdt   VARCHAR(11)  NOT NULL,
  gioitinh  BIT  NOT NULL,
  email  VARCHAR(30)  NOT NULL,
  vaitro BIT NOT NULL,
  PRIMARY KEY (idnguoi_dung),
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
  ngayketthuc DATETIME,
  giatri FLOAT NOT NULL,
  soluongdadung INT NULL,
  trangthai  NVARCHAR(50)  NOT NULL,
  PRIMARY KEY (idVouchers),
);

CREATE TABLE hoa_don (
  idHoaDon  INT NOT NULL IDENTITY(1,1),
  maHoaDon  VARCHAR(50)  NULL,
  idVouchers INT NULL ,
  idKhachHang INT NULL ,
  idnguoi_dung INT  NULL ,
  ngayban   DATETIME DEFAULT GETDATE(),
  tongtien  FLOAT NULL,
  trangthai INT NULL, 
  PRIMARY KEY (idHoaDon),
  FOREIGN KEY (idVouchers) REFERENCES vouchers(idVouchers),
  FOREIGN KEY (idKhachHang) REFERENCES khach_hang (idKhachHang),
  FOREIGN KEY (idnguoi_dung) REFERENCES Nguoi_dung (idnguoi_dung),
);

-- Chuyển idGongKinh và idTrongKinh từ NOT NULL thành NULL
CREATE TABLE hoa_don_chi_tiet (
  idHoaDonChiTiet  INT NOT NULL IDENTITY(1,1),
  id_sp_chi_tiet   INT NOT NULL,
  idHoaDon         INT NOT NULL,
  soluong          INT NOT NULL,
  dongia           FLOAT NOT NULL,
  PRIMARY KEY (idHoaDonChiTiet),
  FOREIGN KEY (id_sp_chi_tiet) REFERENCES san_pham_chi_tiet(id_sp_chi_tiet),
  FOREIGN KEY (idHoaDon) REFERENCES hoa_don(idHoaDon),
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
INSERT INTO san_pham_chi_tiet(idsp,idChatLieu,idMauSac,doCan,giaThanh,giaNhap,soLuong,hinhanh,moTa,trangThai)
VALUES (1, 2 , 4 ,0.5,333000,300000,100,null,N'Sản phẩm thân thiện',N'Đang bán'),
       (1, 2 , 4 ,0.4,883000,300000,201, null,N'Sản phẩm thân thiện',N'Đang bán'),
       (2, 3 , 5 ,1.1,455000,300000,313, null,N'Sản phẩm thân thiện',N'Đang bán'),
       (3, 4 , 3 ,null,489000,300000,298,null,N'Sản phẩm thân thiện',N'Đang bán');


-- Bảng Nhân viên
INSERT INTO Nguoi_dung(maNguoiDung,tenNguoi_dung, ngaysinh,matKhau, sdt, gioitinh, email,vaitro)
VALUES (N'NV01',N'Nguyễn Văn A', '4-15-2005','000000','0365796964', 1 ,'nguyena@gmail.com',1),
       (N'NV02',N'Nguyễn Văn B', '7-7-1999','001234' , '0348123364', 1 ,'nguyenb@gmail.com',0),
       (N'NV03',N'Nguyễn Văn C', '4-4-2007','002157' , '0335345964', 0 ,'nguyenc@gmail.com',1),
       (N'NV04',N'Nguyễn Văn D', '8-4-1998','003afg' , '0348556496', 0 ,'nguyend@gmail.com',0),
       (N'NV05',N'Nguyễn Văn E', '7-19-2003','009fff', '0348358657', 0 ,'nguyene@gmail.com',0);


-- Bảng Vouchers
INSERT INTO vouchers (maVouchers, tenVouchers, soluong, soluongdadung,ngayketthuc, giatri, trangthai)
VALUES
  ('MA001', N'Voucher giảm giá sinh nhật', 100, 0,'2023-12-31', 20000, N'Còn hạn'),
  ('MA002', N'Voucher giảm đơn hàng đầu', 50, 0,'2023-06-30', 50000, N'Còn hạn'),
  ('MA003', N'Voucher giảm 100k cho khách hàng mới', 200,0 ,'2024-5-31', 100000, N'Còn hạn'), 
  ('MA004', N'Voucher Freeship mừng khai trương', 300, 0,'2023-3-31', 55000, N'Còn hạn');


-- Bảng Khách hàng
INSERT INTO khach_hang(maKhachHang, tenKhachHang, diachi, sdt)
VALUES ('KH00',N'Vãng Lai',N'Null',   'null'),
       ('KH02',N'Nguyễn Văn Chung',N'Hà Nam',   '0348775757'),
       ('KH03',N'Lê Đức Đat',N'Thanh Hóa','0348575435'),
       ('KH04',N'Lê Văn Sơn',N'HCM',      '0348597854'),
       ('KH05',N'Nguyễn Hà Chi',N'Cà Mau',   '0348454864');


-- Bảng hóa đơn
INSERT INTO hoa_don(maHoaDon,idVouchers,idKhachHang,idnguoi_dung,tongtien,trangthai)
VALUES ( 'HD002' , null,2 , 3, null ,0),
( 'HD003' , null,3 , 3  , null ,0),
( 'HD004' , null,2 , 2 , null ,0),
( 'HD123' , 1,null , 3 , null , 1),
( 'HD111' , 2,null , 5 ,null , 1)
select * from hoa_don

--Bảng Hóa Đơn Chi Tiết 
INSERT INTO hoa_don_chi_tiet(id_sp_chi_tiet, idHoaDon, soluong, dongia)
VALUES (4, 2, 2, 55000),
       (4, 2, 1, 89000),
       (1, 3, 1, 333000),
       (2, 5, 1, 733000),
       (3, 4, 1, 883000);
	   select * from hoa_don_chi_tiet



SELECT tenKhachHang from khach_hang
getConnection

SELECT * FROM loai_sp
SELECT * FROM thuong_hieu
SELECT * FROM mau_sac
SELECT * FROM chat_lieu
SELECT * FROM san_pham
SELECT * FROM san_pham_chi_tiet

SELECT * FROM hoa_don
SELECT * FROM khach_hang
SELECT * FROM vouchers
SELECT * FROM Nguoi_dung
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


SELECT    email, matKhau
FROM         Nguoi_dung
SELECT    idnguoi_dung, maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro, trangthai
FROM         Nguoi_dung
SELECT    maNguoiDung, tenNguoi_dung, ngaysinh, matKhau, sdt, gioitinh, email, vaitro FROM    Nguoi_dung   Nguoi_dung where maNguoiDung like  or tenNguoi_dung like ? or email like ?
SELECT    hoa_don.maHoaDon, Nguoi_dung.maNguoiDung, khach_hang.maKhachHang, hoa_don.ngayban
FROM         hoa_don INNER JOIN
                      Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung INNER JOIN
                      khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang where hoa_don.trangthai =?
SELECT    maHoaDon, idKhachHang, idnguoi_dung, ngayban, trangthai
FROM         hoa_don
SELECT    maHoaDon, idKhachHang, idnguoi_dung, ngayban,trangthai FROM hoa_don
SELECT    hoa_don.idHoaDon, hoa_don.maHoaDon, Nguoi_dung.maNguoiDung, khach_hang.maKhachHang, hoa_don.ngayban, hoa_don.trangthai
FROM         hoa_don LEFT  JOIN
                      Nguoi_dung ON hoa_don.idnguoi_dung = Nguoi_dung.idnguoi_dung LEFT  JOIN
                      khach_hang ON hoa_don.idKhachHang = khach_hang.idKhachHang
SELECT    san_pham.idsp, san_pham.idloai_sp, san_pham.idThuongHieu, san_pham_chi_tiet.idChatLieu, san_pham_chi_tiet.idMauSac, san_pham_chi_tiet.doCan, san_pham_chi_tiet.giaThanh, san_pham_chi_tiet.soLuong, 
                      san_pham_chi_tiet.moTa
FROM         san_pham INNER JOIN
                      san_pham_chi_tiet ON san_pham.idsp = san_pham_chi_tiet.idsp


SELECT top 5 maHoaDon,nd.tenNguoi_dung,hd.ngayban,kh.tenKhachHang
                    from hoa_don hd LEFT JOIN
                    Nguoi_dung nd ON nd.idnguoi_dung = hd.idnguoi_dung LEFT JOIN
                    khach_hang kh ON kh.idKhachHang = hd.idKhachHang
                    where idHoaDon not in (Select top 5 idHoaDon from hoa_don where trangthai = 0) AND trangthai = 0

					Select count(*) from hoa_don where trangthai = 0


SELECT top 5 lsp.tenloai_sp,sp.masp,sp.tensp,cl.tenChatLieu,ms.tenMauSac,
                    th.tenThuongHieu,spct.doCan,spct.giaThanh,spct.soLuong
                    from san_pham_chi_tiet spct LEFT JOIN
                    san_pham sp ON sp.idsp = spct.idsp LEFT JOIN
                    thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu LEFT JOIN
                    loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp LEFT JOIN
                    chat_lieu cl ON cl.idChatLieu = spct.idChatLieu LEFT JOIN
                    mau_sac ms ON ms.idMauSac = spct.idMauSac
                    where spct.id_sp_chi_tiet not in (Select top 5 id_sp_chi_tiet from san_pham_chi_tiet)


Select count(*) from hoa_don_chi_tiet hdct
INNER JOIN hoa_don hd ON hd.idHoaDon = hdct.idHoaDon
WHERE hd.maHoaDon = 'HD003'

SELECT top 5 spct.id_sp_chi_tiet,sp.masp,sp.tensp,cl.tenChatLieu,ms.tenMauSac,th.tenThuongHieu,spct.doCan,hdct.soluong,hdct.dongia
             from san_pham_chi_tiet spct LEFT JOIN
             san_pham sp ON sp.idsp = spct.idsp LEFT JOIN
             thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu LEFT JOIN
             hoa_don_chi_tiet hdct ON hdct.id_sp_chi_tiet = spct.id_sp_chi_tiet LEFT JOIN
             hoa_don hd ON hd.idHoaDon = hdct.idHoaDon LEFT JOIN
             chat_lieu cl ON cl.idChatLieu = spct.idChatLieu LEFT JOIN
             mau_sac ms ON ms.idMauSac = spct.idMauSac
             where hdct.idHoaDonChiTiet not in
             (Select top 5 hdct.idHoaDonChiTiet from hoa_don_chi_tiet hdct
             INNER JOIN hoa_don hd ON hdct.idHoaDon = hd.idHoaDon
             where hd.idHoaDon = 2) AND hd.idHoaDon = 2

Select * from hoa_don_chi_tiet where idHoaDon = 2 AND id_sp_chi_tiet = 4
Delete from hoa_don_chi_tiet where id_sp_chi_tiet = 4

update hoa_don_chi_tiet set soluong = 3 , dongia = ? where id_sp_chi_tiet = 4 AND idHoaDon = 2
UPDATE san_pham_chi_tiet SET soLuong = 99 WHERE id_sp_chi_tiet = 1
Select soLuong from san_pham_chi_tiet where id_sp_chi_tiet = 1

SELECT hd.idHoaDon,hd.maHoaDon,vc.maVouchers,kh.tenKhachHang,nd.tenNguoi_dung,hd.ngayban,hd.tongtien,hd.trangthai 
from hoa_don hd LEFT JOIN
vouchers vc ON vc.idVouchers = hd.idVouchers LEFT JOIN
khach_hang kh ON kh.idKhachHang = hd.idKhachHang LEFT JOIN
Nguoi_dung nd ON nd.idnguoi_dung = hd.idnguoi_dung 
WHERE idHoaDon = 6


Select * from hoa_don_chi_tiet where idHoaDon = 1 

Delete from khach_hang where idKhachHang = 1
SELECT    loai_sp.tenloai_sp, san_pham.tensp, thuong_hieu.tenThuongHieu, mau_sac.tenMauSac, chat_lieu.tenChatLieu, san_pham_chi_tiet.doCan, san_pham_chi_tiet.giaThanh, san_pham_chi_tiet.soLuong, 
                      san_pham_chi_tiet.hinhanh, san_pham_chi_tiet.moTa
FROM         san_pham INNER JOIN
                      san_pham_chi_tiet ON san_pham.idsp = san_pham_chi_tiet.idsp INNER JOIN
                      mau_sac ON san_pham_chi_tiet.idMauSac = mau_sac.idMauSac INNER JOIN
                      loai_sp ON san_pham.idloai_sp = loai_sp.idloai_sp INNER JOIN
                      chat_lieu ON san_pham_chi_tiet.idChatLieu = chat_lieu.idChatLieu INNER JOIN
                      thuong_hieu ON san_pham.idThuongHieu = thuong_hieu.idThuongHieu where san_pham_chi_tiet.trangThai = N'Đang bán'


					  SELECT top 5 spct.id_sp_chi_tiet,lsp.tenloai_sp,sp.masp,sp.tensp,cl.tenChatLieu,ms.tenMauSac,

                                      th.tenThuongHieu,spct.doCan,spct.giaThanh,spct.soLuong,spct.moTa
                                      from san_pham_chi_tiet spct LEFT JOIN
                                        san_pham sp ON sp.idsp = spct.idsp LEFT JOIN
                                  thuong_hieu th ON th.idThuongHieu = sp.idThuongHieu LEFT JOIN
                                      loai_sp lsp ON lsp.idloai_sp = sp.idloai_sp LEFT JOIN
                    chat_lieu cl ON cl.idChatLieu = spct.idChatLieu LEFT JOIN
                    mau_sac ms ON ms.idMauSac = spct.idMauSac
                                        where spct.id_sp_chi_tiet not in (Select top 0 id_sp_chi_tiet from san_pham_chi_tiet)
			SELECT tenVouchers from vouchers where trangthai = N'Còn hạn'
