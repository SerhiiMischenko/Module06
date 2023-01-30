INSERT INTO `myDB`.`worker` (`id`, `name`, `birthday`, `level`, `salary`)
 VALUES ('1', 'John Connor', '19900101', 'Junior', '1200'),
		('2', 'Tolya Reshetov', '19910410', 'Trainee', '300'),
        ('3', 'John Abrucii', '19951101', 'Senior', '5400'),
        ('4', 'Mia Stomp', '19880511', 'Junior', '1200'),
        ('5', 'Alex Bliznuk', '19870101', 'Middle', '1789'),
        ('6', 'Sarra Connor', '19771201', 'Senior', '6100'),
        ('7', 'Serhii Yablonuk', '20020312', 'Trainee', '1000'),
        ('8', 'Stas Koval', '19970101', 'Trainee', '1000'),
        ('9', 'Evgen Pilipchatin', '19910101', 'Junior', '1440'),
        ('10', 'Max Makec', '19850701', 'Senior', '4700');

INSERT INTO `myDB`.`client` (`id`, `name`)
	VALUES ('99', 'Olena Seluk'),
		   ('100', 'Sam Michel'),
           ('101', 'Van Oneell'),
           ('102', 'Andy Panda'),
           ('103', 'Jack Daniels');

INSERT INTO `myDB`.`project` (`id`, `CLIENT_ID`, `START_DATE`, `FINISH_DATE`)
	VALUES ('21', (select id from client where name = 'Andy Panda'), '19990101', '19990201'),
		   ('22', (select id from client where name = 'Andy Panda'), '19991001', '20071010'),
           ('23', (select id from client where name = 'Andy Panda'), '19991001', '20001010'),
           ('24', (select id from client where name = 'Van Oneell'), '20001001', '20021010'),
           ('25', (select id from client where name = 'Jack Daniels'), '19991001', '20041010'),
           ('26', (select id from client where name = 'Jack Daniels'), '20021001', '20031010'),
           ('27', (select id from client where name = 'Van Oneell'), '20011001', '20011110'),
           ('28', (select id from client where name = 'Sam Michel'), '20021001', '20031010'),
           ('29', (select id from client where name = 'Olena Seluk'), '20031001', '20041010'),
           ('30', (select id from client where name = 'Andy Panda'), '20051001', '20071010');

INSERT INTO `myDB`.`project_worker` (`PROJECT_ID`, `WORKER_ID`)
	VALUES ('21', '1'),
		   ('21', '4'),
           ('21', '6'),
           ('22', '1'),
           ('23', '10'),
           ('23', '2'),
           ('24', '3'),
           ('25', '7'),
           ('26', '8'),
           ('27', '9'),
           ('28', '10'),
		   ('28', '2'),
           ('29', '3'),
           ('29', '5'),
           ('30', '5');
