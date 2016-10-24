import MySQLdb


class database:

	def __init__(self):
		#Open database connector
		self.db = MySQLdb.connect("localhost","root"," ")
		self.cursor = self.db.cursor()
		#Create SCheme
		sql = 'CREATE SCHEMA `magazine` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;'
		self.cursor.execute(sql)
		#Create Table
		sql = "CREATE TABLE IF NOT EXISTS `magazine`.`magazine` ( \
				`id` INT NOT NULL AUTO_INCREMENT COMMENT '', \
			 	`type` VARCHAR(45) NOT NULL COMMENT '', \
				`title` VARCHAR(45) NOT NULL COMMENT '', \
				`content` MEDIUMTEXT NULL COMMENT '', \
				`image` VARCHAR(45) NULL COMMENT '', \
				`link` VARCHAR(45) NULL COMMENT '', \
				`date` VARCHAR(45) NULL COMMENT '', \
				`code` VARCHAR(45) NOT NULL COMMENT '', \
				PRIMARY KEY (`id`, `type`, `code`)  COMMENT '')";
		self.cursor.execute(sql)


