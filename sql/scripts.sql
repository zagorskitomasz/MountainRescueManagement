use `heroku_5c0eab382ed98b6`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS rescuer_detail;

CREATE TABLE rescuer_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  address varchar(128) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  phone_number varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS rescuer;

CREATE TABLE rescuer (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  rescuer_detail_id int(11) DEFAULT NULL,
  state varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_DETAIL_idx (rescuer_detail_id),
  CONSTRAINT FK_DETAIL FOREIGN KEY (rescuer_detail_id) 
  REFERENCES rescuer_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS operation_detail;

CREATE TABLE operation_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(300) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS operation;

CREATE TABLE operation (
  id int(11) NOT NULL AUTO_INCREMENT,
  destination varchar(100) DEFAULT NULL,
  operation_detail_id int(11) DEFAULT NULL,
  state varchar(45) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK_OPDETAIL_idx (operation_detail_id),
  CONSTRAINT FK_OPDETAIL FOREIGN KEY (operation_detail_id) 
  REFERENCES operation_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

drop table if exists operation_rescuer;

CREATE TABLE operation_rescuer (
  operation_id int(11) NOT NULL,
  rescuer_id int(11) NOT NULL,
  PRIMARY KEY (operation_id, rescuer_id),
  KEY FK_RESCUER_idx (rescuer_id),
  CONSTRAINT FK_OPERATION_05 FOREIGN KEY (operation_id) 
  REFERENCES operation (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT FK_RESCUER FOREIGN KEY (rescuer_id) 
  REFERENCES rescuer (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;