INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `surname`) VALUES ('1', 'email@email.com', 'Karol', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa', 'Mik');
INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `surname`) VALUES ('2', 'email1@email.com', 'Paweł', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa', 'Zacharz');
INSERT INTO `user` (`id_user`, `email`, `name`, `password`, `surname`) VALUES ('3', 'email2@email.com', 'Wojciech', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa', 'Grelewicz');

INSERT INTO `phone_number` (`id_phone_number`, `number`, `pin`) VALUES ('1', '111111111', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa');
INSERT INTO `phone_number` (`id_phone_number`, `number`, `pin`) VALUES ('2', '222222222', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa');
INSERT INTO `phone_number` (`id_phone_number`, `number`, `pin`) VALUES ('3', '333333333', '$2a$10$cSpGppzIKqHy8VYOxOqwrOZxYh8zhZ4uv0eMveLNv2u83QS9t5mTa');

INSERT INTO `user_phone_number` (`id_user_phone_number`, `id_phone_number`, `id_user`) VALUES ('1', '1', '1');
INSERT INTO `user_phone_number` (`id_user_phone_number`, `id_phone_number`, `id_user`) VALUES ('2', '2', '2');
INSERT INTO `user_phone_number` (`id_user_phone_number`, `id_phone_number`, `id_user`) VALUES ('3', '3', '3');

INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('1', 'More SMS for you. In the 10 PLN package, you get 100 SMS to use.The package fee is one-time and will be added to your invoice.', '100SMS', '0', '0', '100', '10', 1);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('2', 'More SMS for you. In the 20 PLN package, you get 200 SMS to use.  The package fee is one-time and will be added to your invoice. ', '200SMS', '0', '0', '200', '20', 1);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('3', 'More SMS for you. In the 30 PLN package, you get 300 SMS to use.The package fee is one-time and will be added to your invoice', '300SMS', '0', '0', '300', '30', 1);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('4', 'More minutes for you. In the 10 PLN package, you get 100 minutes to use. The package fee is one-time and will be added to your invoice.', '100MIN', '0', '100', '0', '10', 2);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('5', 'More minutes for you. In the 20 PLN package, you get 200 minutes to use. The package fee is one-time and will be added to your invoice.', '200MIN', '0', '200', '0', '20', 2);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('6', 'More minutes for you. In the 30 PLN package, you get 300 minutes to use. The package fee is one-time and will be added to your invoice.', '300MIN', '0', '300', '0', '30', 2);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('7', 'More internet for you. In the 10 PLN package, you get 100MB of internet to use.The package fee is one-time and will be added to your invoice.', '100MB', '100', '0', '0', '10', 3);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('8', 'More internet for you. In the 20 PLN package, you get 200MB of internet to use.The package fee is one-time and will be added to your invoice.', '200MB', '200', '0', '0', '20', 3);
INSERT INTO `package` (`id`, `description`, `name_package`, `number_of_internet`, `number_of_minutes`, `number_of_sms`, `price`, `package_type`) VALUES ('9', 'More internet for you. In the 30 PLN package, you get 300MB of internet to use. The package fee is one-time and will be added to your invoice.', '300MB', '300', '0', '0', '30', 3);

INSERT INTO `contracts` (`id_contract`, `contract_minutes`, `contract_sms`, `contracts_internet`, `monthly_cost`, `name`) VALUES ('1', '200', '200', '200', '30', 'Start Package');

INSERT INTO `purchased_packages` (`id_purchase_package`, `date_purchase_date`, `a_package_id`, `phone_number_id_phone_number`) VALUES ('1', '2021-06-08', '1', '1');
INSERT INTO `purchased_packages` (`id_purchase_package`, `date_purchase_date`, `a_package_id`, `phone_number_id_phone_number`) VALUES ('2', '2021-06-08', '2', '2');
INSERT INTO `purchased_packages` (`id_purchase_package`, `date_purchase_date`, `a_package_id`, `phone_number_id_phone_number`) VALUES ('3', '2021-06-08', '3', '3');

INSERT INTO `invoices` (`id_invoice`, `invoice_number`, `date_invoice`, `price`, `status_invoice`, `user_id_user`) VALUES ('1', 'OP/1/05/05/2021', '2021-05-05', '90', '1', '1');
INSERT INTO `invoices` (`id_invoice`, `invoice_number`, `date_invoice`, `price`, `status_invoice`, `user_id_user`) VALUES ('2', 'OP/2/05/05/2021', '2021-05-05', '80', '1', '2');
INSERT INTO `invoices` (`id_invoice`, `invoice_number`, `date_invoice`, `price`, `status_invoice`, `user_id_user`) VALUES ('3', 'OP/3/05/06/2021', '2021-06-05', '100', '0', '1');