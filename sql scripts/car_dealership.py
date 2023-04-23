ROWS_TO_GENERATE = 10
ROWS_PER_BATCH = 10

from faker import Faker
from faker_vehicle import VehicleProvider

class CarDealership:
    def __init__(self, name, address, phone, founding_year, email_address):
        self.name = name
        self.address = address
        self.phone = phone
        self.founding_year = founding_year
        self.email_address = email_address

    def __str__(self):
        return f'{self.name}, {self.address}, {self.phone}, {self.founding_year}, {self.email_address}'


def generate_car_dealerships(amount):
    faker: Faker = Faker()
    faker.add_provider(VehicleProvider)
    car_dealerships = []
    for i in range(amount):

        if i % ROWS_PER_BATCH == 0:
            print(f"Generated {i} rows")

        name = faker.company()
        address = faker.address()
        phone = faker.phone_number()
        founding_year = faker.random_int(1960, 2021)
        email_address = faker.email()

        car_dealerships.append(CarDealership(name, address, phone, founding_year, email_address))

    return car_dealerships


def generate_sql(CarDealerships):
    with open("car_dealerships.sql", "w") as file:
        file.write("TRUNCATE TABLE car_dealership RESTART IDENTITY CASCADE;")

    sql = "INSERT INTO car_dealership (name, address, phone, founding_year, email_address) VALUES "
    i = 0
    for CarDealership in CarDealerships:
        sql += f"('{CarDealership.name}', '{CarDealership.address}', '{CarDealership.phone}', '{CarDealership.founding_year}', '{CarDealership.email_address}'),"
        if i % ROWS_PER_BATCH == 0:
            # write the sql to a file

            with open("car_dealerships.sql", "a") as file:
                file.write(sql[:-1] + ";\n")

            print(f"Written {i} rows to file")
            sql = "INSERT INTO car_dealership (name, address, phone, founding_year, email_address) VALUES "

        i += 1

    if sql != "INSERT INTO car_dealership (name, address, phone, founding_year, email_address) VALUES ":
        with open("car_dealerships.sql", "a") as file:
            file.write(sql[:-1] + ";")
        print(f"Written {i} rows to file - last batch")

    print("Done! :")


if __name__ == '__main__':
    courses = generate_car_dealerships(ROWS_TO_GENERATE)
    generate_sql(courses)