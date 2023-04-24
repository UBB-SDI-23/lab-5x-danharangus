ROWS_TO_GENERATE = 1000000
ROWS_PER_BATCH = 1000

from faker import Faker
from faker_vehicle import VehicleProvider

class Car:
    def __init__(self, make, model, year, price, is_electric, description):
        self.make = make
        self.model = model
        self.year = year
        self.price = price
        self.is_electric = is_electric
        self.description = description

    def __str__(self):
        return f'{self.make}, {self.model}, {self.year}, {self.price}, {self.is_electric}, {self.description}'


def generate_cars(amount):
    faker: Faker = Faker()
    faker.add_provider(VehicleProvider)
    cars = []
    for i in range(amount):

        if i % ROWS_PER_BATCH == 0:
            print(f"Generated {i} rows")

        make = faker.vehicle_make()
        model = faker.vehicle_model()
        year = faker.year()
        price = faker.random_int(10000, 50000)
        is_electric = faker.random_int(0, 1)
        description = faker.text()

        cars.append(Car(make, model, year, price, is_electric, description))

    return cars


def generate_sql(cars):
    with open("cars.sql", "w") as file:
        file.write("TRUNCATE TABLE Car RESTART IDENTITY CASCADE;")

    sql = "INSERT INTO Car (make, model, year, price, is_electric, description) VALUES "
    i = 0
    for car in cars:
        sql += f"('{car.make}', '{car.model}', '{car.year}', '{car.price}', '{car.is_electric}', '{car.description}'),"
        if i % ROWS_PER_BATCH == 0:
            # write the sql to a file

            with open("cars.sql", "a") as file:
                file.write(sql[:-1] + ";\n")

            print(f"Written {i} rows to file")
            sql = "INSERT INTO Car (make, model, year, price, is_electric, description) VALUES "

        i += 1

    if sql != "INSERT INTO Car (make, model, year, price, isElectric, description) VALUES ":
        with open("cars.sql", "a") as file:
            file.write(sql[:-1] + ";")
        print(f"Written {i} rows to file - last batch")

    print("Done! :")


if __name__ == '__main__':
    courses = generate_cars(ROWS_TO_GENERATE)
    generate_sql(courses)