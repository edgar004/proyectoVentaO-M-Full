-- phpMyAdmin SQL Dump
-- version 4.6.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-12-2019 a las 18:54:06
-- Versión del servidor: 5.7.12-log
-- Versión de PHP: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `facturacion99`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `id_art` int(5) UNSIGNED ZEROFILL NOT NULL,
  `des_art` varchar(100) NOT NULL,
  `cant` float(12,2) NOT NULL,
  `pre_com` float(12,2) NOT NULL,
  `pre_mayor` float(12,2) NOT NULL,
  `pre_consumidor` float(12,2) NOT NULL,
  `reorden` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`id_art`, `des_art`, `cant`, `pre_com`, `pre_mayor`, `pre_consumidor`, `reorden`) VALUES
(00006, 'sandia ', 133.00, 14.00, 24.00, 30.00, 10),
(00009, 'coca cola', 70.00, 95.00, 100.00, 100.00, 10),
(00010, 'aji', 14.00, 22.00, 25.00, 25.00, 10),
(00011, 'pan', 16.00, 45.00, 55.00, 50.00, 10),
(00013, 'papel de baño', 35.00, 14.00, 18.00, 20.00, 20),
(00014, 'aguacate', 15.00, 15.00, 20.00, 20.00, 10),
(00022, 'sal', 11.00, 10.00, 12.00, 15.00, 11),
(00023, 'platano', 79.00, 5.00, 8.00, 10.00, 5),
(00025, 'salami', 10.00, 150.00, 180.00, 200.00, 5),
(00026, 'saco maiz', -13.00, 1500.00, 1800.00, 1800.00, 1),
(00027, 'guineo', 32.00, 3.00, 5.00, 8.00, 10),
(00028, 'papa', 44.00, 20.00, 25.00, 30.00, 5),
(00029, 'sardina', 18.00, 50.00, 55.00, 65.00, 10),
(00030, 'saco de arroz', 6.00, 1000.00, 1800.00, 2000.00, 1),
(00031, 'cerveza', 5.00, 80.00, 100.00, 120.00, 5),
(00032, 'tomate', 5.00, 15.00, 25.00, 25.00, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idcliente` int(5) UNSIGNED ZEROFILL NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `cedula` varchar(100) NOT NULL,
  `limite_credito` float(12,2) NOT NULL,
  `sector` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `estado` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idcliente`, `nombre`, `apellido`, `cedula`, `limite_credito`, `sector`, `calle`, `ciudad`, `estado`) VALUES
(00001, 'wilson', 'morel', '402-5555555-5', 10000.00, 'cienfuegos', 'calle5', 'santiago', 'ACTIVO'),
(00002, 'jose', 'perez', '031-5555555-5', 5000.00, 'pekin', 'calle5', 'santiago', 'INACTIVO'),
(00003, 'oscar', 'almonte', '656-5615151-5', 100000.00, 'gurabo', '19', 'santiago', 'ACTIVO'),
(00007, 'pedro', 'garcia', '402-5454545-4', 5000.00, 'cienfuegos', 'calle4', 'santiaago', 'ACTIVO'),
(00008, 'melissa', 'ramos', '402-5874964-1', 10000.00, 'francisco', 'moncruz', 'santiago', 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contador`
--

CREATE TABLE `contador` (
  `id_art` int(5) NOT NULL,
  `idcliente` int(5) NOT NULL,
  `idfactura` int(5) NOT NULL,
  `idempleado` int(5) NOT NULL,
  `idproveedor` int(5) NOT NULL,
  `idcompra` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `contador`
--

INSERT INTO `contador` (`id_art`, `idcliente`, `idfactura`, `idempleado`, `idproveedor`, `idcompra`) VALUES
(16, 11, 105, 4, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contador_compra`
--

CREATE TABLE `contador_compra` (
  `compra` int(11) NOT NULL,
  `total_compra` float(12,2) NOT NULL,
  `fecha` date NOT NULL,
  `tipo_compra` varchar(10) NOT NULL,
  `balance` float(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `contador_compra`
--

INSERT INTO `contador_compra` (`compra`, `total_compra`, `fecha`, `tipo_compra`, `balance`) VALUES
(1, 13.00, '2019-12-12', 'CONTADO', 13.00),
(2, 102.00, '2019-12-12', 'CREDITO', 0.01),
(3, 120.00, '2019-12-12', 'CONTADO', 120.00),
(4, 240.00, '2019-12-14', 'CREDITO', 0.00),
(5, 6000.00, '2019-12-14', 'CREDITO', 3000.00),
(6, 2500.00, '2019-12-14', 'CREDITO', 2500.00),
(7, 2500.00, '2019-12-14', 'CONTADO', 2500.00),
(8, 2500.00, '2019-12-14', 'CONTADO', 2500.00),
(9, 150.00, '2019-12-21', 'CONTADO', 150.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_comp`
--

CREATE TABLE `detalle_comp` (
  `num_compra` int(11) DEFAULT NULL,
  `codigo` varchar(20) DEFAULT 'NA',
  `cantidad` float(12,2) DEFAULT '0.00',
  `precio` float(12,2) DEFAULT '0.00',
  `importe` float(12,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_comp`
--

INSERT INTO `detalle_comp` (`num_compra`, `codigo`, `cantidad`, `precio`, `importe`) VALUES
(1, '00022', 1.00, 10.00, 10.00),
(1, '00027', 1.00, 3.00, 3.00),
(2, '00022', 1.00, 10.00, 10.00),
(2, '00029', 1.00, 50.00, 50.00),
(2, '00013', 3.00, 14.00, 42.00),
(3, '00029', 1.00, 50.00, 50.00),
(3, '00013', 5.00, 14.00, 70.00),
(4, '00006', 5.00, 14.00, 70.00),
(4, '00029', 2.00, 50.00, 100.00),
(4, '00013', 5.00, 14.00, 70.00),
(5, '00030', 3.00, 2000.00, 6000.00),
(6, '00030', 1.00, 2500.00, 2500.00),
(7, '00030', 1.00, 2500.00, 2500.00),
(8, '00030', 1.00, 2500.00, 2500.00),
(9, '00014', 10.00, 15.00, 150.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_fact`
--

CREATE TABLE `detalle_fact` (
  `no_factura` varchar(100) CHARACTER SET armscii8 NOT NULL,
  `id_art` varchar(100) NOT NULL,
  `cant` float(12,2) NOT NULL,
  `precio` float(12,2) NOT NULL,
  `costo` float(12,2) NOT NULL,
  `importe` float(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_fact`
--

INSERT INTO `detalle_fact` (`no_factura`, `id_art`, `cant`, `precio`, `costo`, `importe`) VALUES
('47', '00013', 1.00, 18.00, 14.00, 18.00),
('48', '00022', 1.00, 15.00, 15.00, 15.00),
('49', '00022', 1.00, 15.00, 15.00, 15.00),
('49', '00013', 1.00, 18.00, 14.00, 18.00),
('49', '00021', 1.00, 6000.00, 5000.00, 6000.00),
('50', '00006', 5.00, 24.00, 14.00, 120.00),
('51', '00006', 5.00, 24.00, 14.00, 120.00),
('52', '00013', 1.00, 18.00, 14.00, 18.00),
('53', '00023', 5.00, 10.00, 5.00, 50.00),
('54', '00014', 10.00, 25.00, 15.00, 250.00),
('55', '00009', 10.00, 110.00, 95.00, 1100.00),
('56', '00023', 2.00, 10.00, 5.00, 20.00),
('56', '00014', 2.00, 25.00, 15.00, 50.00),
('57', '00023', 1.00, 10.00, 5.00, 10.00),
('58', '00009', 1.00, 110.00, 95.00, 110.00),
('59', '00023', 2.00, 10.00, 5.00, 20.00),
('59', '00023', 5.00, 10.00, 5.00, 50.00),
('59', '00023', 1.00, 10.00, 5.00, 10.00),
('59', '00013', 5.00, 18.00, 15.00, 90.00),
('59', '00013', 5.00, 18.00, 15.00, 90.00),
('59', '00006', 2.00, 24.00, 15.00, 48.00),
('60', '00011', 5.00, 55.00, 45.00, 275.00),
('60', '00013', 5.00, 18.00, 14.00, 90.00),
('61', '00023', 1.00, 10.00, 15.00, 10.00),
('61', '00014', 5.00, 25.00, 15.00, 125.00),
('62', '00026', 1.00, 2000.00, 1500.00, 2000.00),
('62', '00028', 5.00, 30.00, 95.00, 150.00),
('62', '00011', 3.00, 55.00, 95.00, 165.00),
('62', '00006', 1.00, 24.00, 95.00, 24.00),
('62', '00009', 3.00, 110.00, 95.00, 330.00),
('62', '00026', 2.00, 2000.00, 1500.00, 4000.00),
('63', '00022', 2.00, 12.00, 10.00, 24.00),
('64', '00022', 3.00, 15.00, 10.00, 45.00),
('65', '00025', 2.00, 200.00, 150.00, 400.00),
('65', '00013', 1.00, 20.00, 14.00, 20.00),
('65', '00014', 1.00, 20.00, 15.00, 20.00),
('65', '00014', 1.00, 20.00, 15.00, 20.00),
('66', '00013', 2.00, 18.00, 14.00, 36.00),
('66', '00025', 1.00, 200.00, 20.00, 200.00),
('66', '00028', 1.00, 30.00, 20.00, 30.00),
('67', '00011', 2.00, 50.00, 95.00, 100.00),
('67', '00009', 1.00, 100.00, 95.00, 100.00),
('67', '00010', 1.00, 25.00, 1500.00, 25.00),
('67', '00026', 2.00, 1800.00, 1500.00, 3600.00),
('67', '00032', 5.00, 25.00, 10.00, 125.00),
('67', '00022', 2.00, 15.00, 10.00, 30.00),
('68', '00025', 1.00, 200.00, 3.00, 200.00),
('68', '00027', 3.00, 8.00, 3.00, 24.00),
('69', '00025', 1.00, 200.00, 1000.00, 200.00),
('69', '00009', 5.00, 100.00, 1000.00, 500.00),
('69', '00027', 4.00, 8.00, 1000.00, 32.00),
('69', '00030', 2.00, 1800.00, 1000.00, 3600.00),
('70', '00026', 1.00, 1800.00, 15.00, 1800.00),
('70', '00025', 2.00, 200.00, 15.00, 400.00),
('70', '00014', 2.00, 20.00, 15.00, 40.00),
('70', '00014', 1.00, 20.00, 14.00, 20.00),
('70', '00006', 1.00, 30.00, 14.00, 30.00),
('71', '00027', 2.00, 8.00, 22.00, 16.00),
('71', '00028', 4.00, 30.00, 22.00, 120.00),
('71', '00010', 4.00, 25.00, 22.00, 100.00),
('000000000072', '00031', 2.00, 70.00, 20.00, 140.00),
('000000000072', '00028', 1.00, 30.00, 20.00, 30.00),
('000000000073', '00025', 2.00, 200.00, 150.00, 400.00),
('000000000074', '00011', 2.00, 50.00, 14.00, 100.00),
('000000000074', '00006', 4.00, 30.00, 14.00, 120.00),
('000000000078', '00027', 5.00, 8.00, 3.00, 40.00),
('000000000079', '00014', 1.00, 20.00, 100.00, 20.00),
('000000000079', '00031', 2.00, 70.00, 100.00, 140.00),
('000000000080', '00026', 1.00, 1800.00, 1500.00, 1800.00),
('000000000081', '00028', 1.00, 30.00, 20.00, 30.00),
('000000000082', '00031', 1.00, 70.00, 20.00, 70.00),
('000000000082', '00028', 2.00, 30.00, 20.00, 60.00),
('000000000083', '00026', 2.00, 1800.00, 1500.00, 3600.00),
('84', '00027', 2.00, 8.00, 3.00, 16.00),
('85', '00029', 2.00, 65.00, 50.00, 130.00),
('86', '00029', 2.00, 65.00, 50.00, 130.00),
('87', '00022', 5.00, 15.00, 10.00, 75.00),
('000000088', '00026', 4.00, 1800.00, 1500.00, 7200.00),
('000000089', '00027', 5.00, 8.00, 3.00, 40.00),
('000000090', '00025', 1.00, 200.00, 150.00, 200.00),
('000000091', '00029', 4.00, 65.00, 50.00, 260.00),
('000000092', '00023', 1.00, 10.00, 5.00, 10.00),
('000000093', '00023', 1.00, 10.00, 5.00, 10.00),
('000000094', '00028', 1.00, 30.00, 20.00, 30.00),
('000000095', '00014', 4.00, 20.00, 15.00, 80.00),
('000000096', '00027', 4.00, 8.00, 3.00, 32.00),
('000000098', '00028', 1.00, 30.00, 20.00, 30.00),
('000000098', '00029', 5.00, 65.00, 50.00, 325.00),
('000000099', '00025', 4.00, 200.00, 150.00, 800.00),
('0000000100', '00025', 4.00, 200.00, 150.00, 800.00),
('0000000101', '00028', 1.00, 30.00, 20.00, 30.00),
('0000000102', '00023', 5.00, 10.00, 5.00, 50.00),
('0000000103', '00027', 2.00, 8.00, 3.00, 16.00),
('0000000104', '00026', 4.00, 1800.00, 1500.00, 7200.00),
('0000000105', '00009', 2.00, 100.00, 1500.00, 200.00),
('0000000105', '00014', 2.00, 20.00, 1500.00, 40.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idempleado` int(5) UNSIGNED ZEROFILL NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `cedula` varchar(100) NOT NULL,
  `limite_credito` float(12,2) NOT NULL,
  `sector` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `fecha_nac` date NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `estado` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idempleado`, `nombre`, `apellido`, `cedula`, `limite_credito`, `sector`, `calle`, `ciudad`, `fecha_nac`, `fecha_ingreso`, `estado`) VALUES
(00015, 'melissa', 'ramos', '402-5897854-4', 100000.00, 'francisco', 'moncruz', 'santiago', '1997-05-10', '2019-10-22', 'ACTIVO'),
(00016, 'winnifer', 'baez', '402-6598746-5', 5000.00, 'cienfuego', '20', 'santiago', '2019-12-05', '2019-12-20', 'ACTIVO'),
(00017, 'nicaury', 'ramos', '809-4575157-4', 12000.00, 'francisco', 'moncruz', 'santiago', '2019-12-01', '2019-12-21', 'ACTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `no_factura` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `sub_total` float(12,2) NOT NULL,
  `itbis` float(12,2) NOT NULL,
  `monto` float(12,2) NOT NULL,
  `total` float(12,2) NOT NULL,
  `idcliente` int(5) NOT NULL,
  `idusuario` int(5) NOT NULL,
  `tipo_factura` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`no_factura`, `fecha`, `sub_total`, `itbis`, `monto`, `total`, `idcliente`, `idusuario`, `tipo_factura`) VALUES
('2', '2018-08-30', 984.00, 0.00, 984.00, 0.00, 1, 3, 'CREDITO'),
('4', '2018-08-30', 960.00, 0.00, 960.00, 0.00, 1, 3, 'CREDITO'),
('5', '2018-08-30', 1920.00, 0.00, 1920.00, 0.00, 1, 3, 'CREDITO'),
('10', '2019-04-07', 6000.00, 0.00, 6000.00, 6000.00, 5, 5, 'CREDITO'),
('19', '2019-04-06', 12000.00, 0.00, 12000.00, 12000.00, 5, 5, 'CREDITO'),
('28', '2019-04-06', 270.00, 0.00, 270.00, 270.00, 5, 5, 'CREDITO'),
('31', '2019-04-07', 6000.00, 0.00, 6000.00, 6000.00, 5, 10, 'CREDITO'),
('39', '2019-04-08', 15.00, 0.00, 15.00, 15.00, 5, 10, 'CREDITO'),
('40', '2019-04-08', 18.00, 0.00, 18.00, 18.00, 5, 10, 'CREDITO'),
('42', '2019-04-15', 18110.00, 0.00, 18110.00, 18110.00, 5, 10, 'CREDITO'),
('45', '2019-04-16', 6000.00, 0.00, 6000.00, 6000.00, 5, 10, 'CREDITO'),
('54', '2019-04-17', 250.00, 0.00, 250.00, 250.00, 6, 10, 'CREDITO'),
('57', '2019-08-19', 10.00, 0.00, 10.00, 10.00, 6, 10, 'CREDITO'),
('63', '2019-11-12', 24.00, 0.00, 24.00, -20.00, 2, 15, 'CREDITO'),
('64', '2019-11-12', 45.00, 0.00, 45.00, 0.00, 1, 15, 'CREDITO'),
('66', '2019-11-25', 230.00, 0.00, 230.00, 0.00, 1, 15, 'CREDITO'),
('67', '2019-12-15', 155.00, 0.00, 155.00, 0.00, 1, 15, 'CREDITO'),
('68', '2019-12-16', 224.00, 0.00, 224.00, 0.00, 2, 15, 'CREDITO'),
('69', '2019-12-16', 4332.00, 0.00, 4332.00, 2050.00, 3, 15, 'CREDITO'),
('71', '2019-12-17', 236.00, 0.00, 236.00, 206.00, 7, 15, 'CREDITO'),
('000000000072', '2019-12-17', 170.00, 0.00, 170.00, 70.00, 2, 15, 'CREDITO'),
('000000000073', '2019-12-18', 400.00, 0.00, 400.00, 400.00, 3, 15, 'CREDITO'),
('000000000075', '2019-12-18', 1800.00, 0.00, 1800.00, 0.00, 3, 15, 'CREDITO'),
('000000000076', '2019-12-18', 8.00, 0.00, 8.00, 0.00, 2, 15, 'CREDITO'),
('000000000077', '2019-12-18', 1800.00, 0.00, 1800.00, 0.00, 2, 15, 'CREDITO'),
('000000000078', '2019-12-18', 40.00, 0.00, 40.00, 40.00, 3, 15, 'CREDITO'),
('000000000080', '2019-12-18', 1800.00, 0.00, 1800.00, 1800.00, 3, 15, 'CREDITO'),
('000000000081', '2019-12-18', 30.00, 0.00, 30.00, 30.00, 3, 15, 'CREDITO'),
('000000000082', '2019-12-18', 130.00, 0.00, 130.00, 130.00, 3, 15, 'CREDITO'),
('000000000083', '2019-12-18', 3600.00, 0.00, 3600.00, 3600.00, 3, 15, 'CREDITO'),
('84', '2019-12-18', 16.00, 0.00, 16.00, 16.00, 3, 15, 'CREDITO'),
('85', '2019-12-18', 130.00, 0.00, 130.00, 30.00, 7, 15, 'CREDITO'),
('86', '2019-12-18', 130.00, 0.00, 130.00, -70.00, 7, 15, 'CREDITO'),
('87', '2019-12-18', 75.00, 0.00, 75.00, 75.00, 7, 15, 'CREDITO'),
('000000088', '2019-12-18', 7200.00, 0.00, 7200.00, 7000.00, 7, 15, 'CREDITO'),
('000000089', '2019-12-18', 40.00, 0.00, 40.00, 40.00, 3, 15, 'CREDITO'),
('000000090', '2019-12-18', 200.00, 0.00, 200.00, 200.00, 3, 15, 'CREDITO'),
('000000091', '2019-12-18', 260.00, 0.00, 260.00, 260.00, 7, 15, 'CREDITO'),
('000000092', '2019-12-18', 10.00, 0.00, 10.00, 10.00, 3, 15, 'CREDITO'),
('000000093', '2019-12-18', 10.00, 0.00, 10.00, 10.00, 2, 15, 'CREDITO'),
('000000094', '2019-12-18', 30.00, 0.00, 30.00, 30.00, 3, 15, 'CREDITO'),
('000000095', '2019-12-18', 80.00, 0.00, 80.00, 80.00, 3, 15, 'CREDITO'),
('000000096', '2019-12-18', 32.00, 0.00, 32.00, 32.00, 3, 15, 'CREDITO'),
('000000097', '2019-12-18', 30.00, 0.00, 30.00, 0.00, 3, 15, 'CREDITO'),
('000000098', '2019-12-18', 325.00, 0.00, 325.00, 325.00, 7, 15, 'CREDITO'),
('000000099', '2019-12-18', 800.00, 0.00, 800.00, 0.00, 3, 15, 'CREDITO'),
('0000000100', '2019-12-18', 800.00, 0.00, 800.00, 800.00, 7, 15, 'CREDITO'),
('0000000101', '2019-12-18', 30.00, 0.00, 30.00, 30.00, 3, 15, 'CREDITO'),
('0000000102', '2019-12-18', 50.00, 0.00, 50.00, 50.00, 3, 15, 'CREDITO'),
('0000000103', '2019-12-18', 16.00, 0.00, 16.00, 16.00, 3, 15, 'CREDITO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_normal`
--

CREATE TABLE `factura_normal` (
  `no_factura` varchar(100) NOT NULL,
  `fecha` date NOT NULL,
  `sub_total` float(12,2) NOT NULL,
  `itbis` float(12,2) NOT NULL,
  `monto` float(12,2) NOT NULL,
  `idusuario` int(5) NOT NULL,
  `tipo_factura` varchar(100) NOT NULL,
  `forma_pago` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `factura_normal`
--

INSERT INTO `factura_normal` (`no_factura`, `fecha`, `sub_total`, `itbis`, `monto`, `idusuario`, `tipo_factura`, `forma_pago`) VALUES
('000000000060', '2019-08-21', 90.00, 0.00, 90.00, 10, 'CONTADO', 'EFECTIVO'),
('000000000062', '2019-10-22', 2000.00, 0.00, 2000.00, 15, 'CONTADO', 'EFECTIVO'),
('000000000062', '2019-10-22', 669.00, 0.00, 669.00, 15, 'CONTADO', 'EFECTIVO'),
('000000000067', '2019-11-25', 200.00, 0.00, 200.00, 15, 'CONTADO', 'EFECTIVO'),
('000000000067', '2019-11-25', 3625.00, 0.00, 3625.00, 15, 'CONTADO', 'EFECTIVO'),
('000000000074', '2019-12-18', 220.00, 0.00, 220.00, 15, 'CONTADO', 'EFECTIVO'),
('000000000079', '2019-12-18', 160.00, 0.00, 160.00, 15, 'CONTADO', 'EFECTIVO'),
('0000000104', '2019-12-18', 7200.00, 0.00, 7200.00, 15, 'CONTADO', 'EFECTIVO'),
('0000000105', '2019-12-21', 240.00, 0.00, 240.00, 15, 'CONTADO', 'EFECTIVO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `fecha` varchar(20) DEFAULT '0000-00-00',
  `num_compra` int(6) DEFAULT NULL,
  `pago` float(12,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`fecha`, `num_compra`, `pago`) VALUES
('2019-12-16', 5, 200.00),
('2019-12-17', 69, 100.00),
('2019-12-17', 69, 100.00),
('2019-12-17', 69, 150.00),
('2019-12-17', 71, 30.00),
('2019-12-17', 72, 100.00),
('2019-12-18', 86, 200.00),
('2019-12-18', 85, 100.00),
('2019-12-21', 5, 1520.00),
('2019-12-21', 75, 1800.00),
('2019-12-21', 77, 1800.00),
('2019-12-21', 76, 8.00),
('2019-12-21', 99, 800.00),
('2019-12-21', 97, 30.00),
('2019-12-21', 88, 200.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_cuentacredito`
--

CREATE TABLE `pago_cuentacredito` (
  `fecha` varchar(20) DEFAULT '0000-00-00',
  `num_compra` int(6) DEFAULT NULL,
  `pago` float(12,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pago_cuentacredito`
--

INSERT INTO `pago_cuentacredito` (`fecha`, `num_compra`, `pago`) VALUES
('14-dic-2019', 2, 2.00),
('14-dic-2019', 2, 4.00),
('14-dic-2019', 2, 50.00),
('14-dic-2019', 2, 40.00),
('14-dic-2019', 2, 8.00),
('14-dic-2019', 4, 40.00),
('14-dic-2019', 5, 1000.00),
('14-dic-2019', 4, 150.00),
('14-dic-2019', 4, 50.00),
('21/12/2019', 5, 2000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `idproveedor` int(6) UNSIGNED ZEROFILL NOT NULL,
  `representante` varchar(100) NOT NULL,
  `razon_social` varchar(100) NOT NULL,
  `rnc` varchar(100) NOT NULL,
  `sector` varchar(100) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `ciudad` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`idproveedor`, `representante`, `razon_social`, `rnc`, `sector`, `calle`, `ciudad`) VALUES
(000001, 'MELISSA RAMOS', 'PROVISIONES RAMOS', '4556120601116515', 'FRANCISCO', 'MONCRUZ', 'SANTIAGO'),
(000003, 'WINNIFER BAEZ', 'COLMADO BAEZ VARGAS', '515151515111651', 'CIENFUEGOS', '20', 'SANTIAGO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_usuario`
--

CREATE TABLE `registro_usuario` (
  `idempleado` int(5) UNSIGNED ZEROFILL NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `registro_usuario`
--

INSERT INTO `registro_usuario` (`idempleado`, `nombre`, `tipo`, `usuario`, `password`) VALUES
(00016, 'winnifer baez', 'Administrador', 'wini', '202cb962ac59075b964b07152d234b70'),
(00015, 'melissa ramos', 'Administrador', 'melisa', '202cb962ac59075b964b07152d234b70'),
(00017, 'nicaury ramos', 'Empleado', 'nicaury', '202cb962ac59075b964b07152d234b70');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_cliente`
--

CREATE TABLE `tabla_cliente` (
  `idcliente` int(5) NOT NULL,
  `id_tipo_tel` int(5) NOT NULL,
  `numero` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tabla_cliente`
--

INSERT INTO `tabla_cliente` (`idcliente`, `id_tipo_tel`, `numero`) VALUES
(1, 2, '   -   -    '),
(1, 1, '809-656-5656'),
(2, 2, '5000.00'),
(2, 1, '809-565-6565'),
(7, 2, '849-666-6666'),
(7, 1, '809-555-5555'),
(8, 2, '829-555-5555'),
(8, 1, '809-222-2222'),
(1, 2, '   -   -    '),
(1, 1, '809-656-5656'),
(2, 2, '   -   -    '),
(2, 1, '809-565-6565'),
(9, 2, '809-545-4554'),
(9, 1, '809-444-4444'),
(3, 2, '   -   -    '),
(3, 1, '809-565-6565'),
(3, 2, '   -   -    '),
(3, 1, '809-565-6565'),
(10, 2, '   -   -    '),
(10, 1, '809-521-1544'),
(11, 2, '809-471-5454'),
(11, 1, '849-582-0369');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_empleado`
--

CREATE TABLE `tabla_empleado` (
  `idempleado` varchar(100) NOT NULL,
  `id_tipo_tel` varchar(100) NOT NULL,
  `numero` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tabla_empleado`
--

INSERT INTO `tabla_empleado` (`idempleado`, `id_tipo_tel`, `numero`) VALUES
('1', '2', '829-411-5151'),
('1', '1', '809-151-2118'),
('2', '2', '849-211-1211'),
('2', '1', '829-151-5151'),
('3', '2', '809-262-6262'),
('3', '1', '809-516-2222'),
('1', '2', '   -   -    '),
('1', '1', '809-565-5656'),
('2', '2', '   -   -    '),
('2', '1', '   -   -    '),
('3', '2', '809-555-5555'),
('3', '1', '111-111-1111'),
('4', '2', '   -   -    '),
('4', '1', '   -   -    '),
('5', '2', '   -   -    '),
('5', '1', '   -   -    '),
('6', '2', '   -   -    '),
('6', '1', '809-666-6666'),
('6', '2', '809-754-6841'),
('6', '1', '849-403-9367'),
('3', '2', '845-551-5151'),
('3', '1', '809-454-5454'),
('4', '2', '809-555-5555'),
('4', '1', '849-555-5555'),
('3', '2', '   -   -    '),
('3', '1', '809-521-1111'),
('3', '2', '809-575-8585'),
('3', '1', '829-457-6262'),
('00016', '2', '   -   -    '),
('00016', '1', '   -   -    '),
('4', '2', '809-474-5111'),
('4', '1', '809-475-1312'),
('00016', '2', '   -   -    '),
('00016', '1', '   -   -    ');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_proveedor`
--

CREATE TABLE `tabla_proveedor` (
  `idproveedor` int(5) NOT NULL,
  `id_tipo_tel` int(5) NOT NULL,
  `numero` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tabla_proveedor`
--

INSERT INTO `tabla_proveedor` (`idproveedor`, `id_tipo_tel`, `numero`) VALUES
(1, 2, '8095857590'),
(1, 1, '8494039367'),
(2, 2, '  8094715454'),
(2, 1, '8297561212');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_tel`
--

CREATE TABLE `tipo_tel` (
  `id_tipo_tel` int(5) NOT NULL,
  `des_tel` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_tel`
--

INSERT INTO `tipo_tel` (`id_tipo_tel`, `des_tel`) VALUES
(1, 'CELULAR'),
(2, 'RESIDENCIAL'),
(3, 'FLOTA'),
(4, 'TRABAJO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventadia`
--

CREATE TABLE `ventadia` (
  `ID_VENTA` int(20) NOT NULL,
  `id_fac` varchar(100) NOT NULL,
  `total` float(12,2) DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventadia`
--

INSERT INTO `ventadia` (`ID_VENTA`, `id_fac`, `total`) VALUES
(1, '2', 130.00),
(2, '82', 3600.00),
(3, '83', 16.00),
(4, '', 130.00),
(5, '', 130.00),
(6, '', 75.00),
(7, '87', 7200.00),
(8, '000000092', 10.00),
(9, '92', 10.00),
(10, '', 30.00),
(11, '94', 80.00),
(12, '', 32.00),
(13, '', 30.00),
(14, '', 325.00),
(15, '', 7200.00),
(16, '98', 56.00),
(17, '98', 800.00),
(18, '', 120.00),
(19, '99', 325.00),
(20, '99', 800.00),
(21, '100', 30.00),
(22, '101', 50.00),
(23, '0000000103', 16.00),
(24, '0000000104', 7200.00),
(25, '0000000105', 240.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id_art`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idcliente`);

--
-- Indices de la tabla `contador_compra`
--
ALTER TABLE `contador_compra`
  ADD PRIMARY KEY (`compra`);

--
-- Indices de la tabla `detalle_comp`
--
ALTER TABLE `detalle_comp`
  ADD KEY `num_compra` (`num_compra`),
  ADD KEY `codigo` (`codigo`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idempleado`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD KEY `num_compra` (`num_compra`);

--
-- Indices de la tabla `pago_cuentacredito`
--
ALTER TABLE `pago_cuentacredito`
  ADD KEY `num_compra` (`num_compra`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`idproveedor`);

--
-- Indices de la tabla `ventadia`
--
ALTER TABLE `ventadia`
  ADD PRIMARY KEY (`ID_VENTA`),
  ADD KEY `id_fac` (`id_fac`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id_art` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idcliente` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `contador_compra`
--
ALTER TABLE `contador_compra`
  MODIFY `compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idempleado` int(5) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `idproveedor` int(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `ventadia`
--
ALTER TABLE `ventadia`
  MODIFY `ID_VENTA` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
