# GerenciadorDeEstoqueA3

Este projeto utiliza do método MVC (Model, view & Control) de programação orientada a objeto. Ou seja o código está dividido em três partes príncipais para o encapsulamento dos métodos. Ele utiliza de um banco de dados local feito e gerênciado no MySQL.

Portanto para que o programa funcione corretamente, deve existir um banco de dados feito em MySQL (recomendo a utilização do mysql workbench) com as seguintes configurações:


Nome: MVC
Service: localhost:3306
login: root
Senha: F!el1pe2003.@0

Criação das tabelas:

drop database if exists MVC;
create database if  not exists MVC;
use MVC;


CREATE TABLE IF NOT EXISTS `amigos` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `contato` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `ferramentas` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45)not null,
  `marca` VARCHAR(45)not null,
  `valor` DECIMAL(10,2)not null,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `emprestimos` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `data_inicial` DATE NOT NULL,
  `data_final` DATE NOT NULL,
  `ID_amigo` INT NOT NULL,
  `amigo_nome`VARCHAR(45) NOT NULL,
  `ID_ferramenta` INT NOT NULL,
  `ferramenta_nome`VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NOT NULL,
  foreign key (ID_amigo) references amigos(ID),
  foreign key (ID_ferramenta) references ferramentas(ID),
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;
