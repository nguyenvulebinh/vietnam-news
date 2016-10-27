import MySQLdb


class database:

	def __init__(self):
		#Open database connector
		self.db = MySQLdb.connect("127.0.0.1","root","",charset='utf8',use_unicode=True)
		self.cursor = self.db.cursor()
		#Create SCheme
		sql = 'CREATE SCHEMA IF NOT EXISTS `magazine`  DEFAULT CHARACTER SET utf8;'
		self.cursor.execute(sql)
		

		#Create Table
		sql = "CREATE TABLE IF NOT EXISTS `magazine`.`magazine` ( \
				`id` INT NOT NULL AUTO_INCREMENT COMMENT '', \
			 	`type` VARCHAR(45) NOT NULL COMMENT '', \
				`title` VARCHAR(200) NOT NULL COMMENT '', \
				`content` MEDIUMTEXT NULL COMMENT '', \
				`image` VARCHAR(150) NULL COMMENT '', \
				`link` VARCHAR(150) NULL COMMENT '', \
				`date` VARCHAR(45) NULL COMMENT '', \
				`code` VARCHAR(45) NOT NULL COMMENT '', \
				PRIMARY KEY (`id`, `type`, `code`)  COMMENT '')";
		self.cursor.execute(sql)



