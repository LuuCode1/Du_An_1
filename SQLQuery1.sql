create database Ung_Dung_Ban_Kinh
go
use Ung_Dung_Ban_Kinh
go
CREATE TABLE chat_lieu (
  idChatLieu INT NOT NULL AUTO_INCREMENT,
  tenChatLieu VARCHAR(255) NOT NULL,
  PRIMARY KEY (idChatLieu)
);

CREATE TABLE mau_sac (
  idMauSac INT NOT NULL AUTO_INCREMENT,
  tenMauSac VARCHAR(255) NOT NULL,
  PRIMARY KEY (idMauSac)
);

CREATE TABLE thuong_hieu (
  idThuongHieu INT NOT NULL AUTO_INCREMENT,
  tenThuongHieu VARCHAR(255) NOT NULL,
  PRIMARY KEY (idThuongHieu)
);

CREATE TABLE hinh_anh (
  hinhAnh VARCHAR(max) NOT NULL,
  linkAnh VARCHAR(max) NOT NULL,
  PRIMARY KEY (hinhAnh)
);

CREATE TABLE gong_kinh (
  idGongKinh INT NOT NULL AUTO_INCREMENT,
  maGongKinh VARCHAR(255) NOT NULL,
  idChatLieu INT NOT NULL,
  idMauSac INT NOT NULL,
  idThuongHieu INT NOT NULL,
  id_hinhAnh INT NOT NULL,
  giaThanh INT NOT NULL,
  soLuong INT NOT NULL,
  moTa TEXT,
  trangThai VARCHAR(255),
  PRIMARY KEY (idGongKinh),
  FOREIGN KEY (idChatLieu) REFERENCES chat_lieu (idChatLieu),
  FOREIGN KEY (idMauSac) REFERENCES mau_sac (idMauSac),
  FOREIGN KEY (idThuongHieu) REFERENCES thuong_hieu (idThuongHieu),
  FOREIGN KEY (id_hinhAnh) REFERENCES hinh_anh (id_hinhAnh)
);
--con tiep
--new

--00000000