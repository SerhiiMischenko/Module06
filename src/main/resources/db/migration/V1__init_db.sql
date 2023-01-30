CREATE TABLE `worker` (
    `id` INT NOT NULL,
    `name` VARCHAR(1000) NOT NULL CHECK (length (name) > 2),
    `birthday` DATE NOT NULL CHECK (birthday > 19000101),
    `level` VARCHAR(10) NOT NULL CHECK (level = 'Trainee' OR level = 'Junior'
        OR level = 'Middle'
        OR level = 'Senior'),
    `salary` INT NOT NULL CHECK (salary >= 100 AND salary < 100000),
    PRIMARY KEY (`id`)
);

CREATE TABLE client (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(1000) NOT NULL CHECK (length (name) > 2),
    PRIMARY KEY (`id`)
);

CREATE TABLE `myDB`.`project` (
  `id` INT NOT NULL,
  `CLIENT_ID` INT NOT NULL,
  `START_DATE` DATE NOT NULL,
  `FINISH_DATE` DATE NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `myDB`.`project_worker` (
  `PROJECT_ID` INT NOT NULL,
  `WORKER_ID` INT NOT NULL,
  PRIMARY KEY (`PROJECT_ID`, `WORKER_ID`));

  ALTER TABLE `myDB`.`project_worker`
ADD CONSTRAINT `id`
  FOREIGN KEY (`PROJECT_ID`)
  REFERENCES `myDB`.`project` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

  ALTER TABLE `myDB`.`project_worker`
ADD CONSTRAINT `worker_id`
  FOREIGN KEY (`WORKER_ID`)
  REFERENCES `myDB`.`worker` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;