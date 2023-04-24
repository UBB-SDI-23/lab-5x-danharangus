ROWS_TO_GENERATE = 1000000
ROWS_PER_BATCH = 1000

from faker import Faker


class Customer:

    def __init__(self, first_name, last_name, age, email, phone_number):
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.email = email
        self.phone_number = phone_number

    def __str__(self):
        return f'{self.first_name}, {self.last_name}, {self.age}, {self.email}, {self.phone_number}'


def generate_customers(amount):

    faker: Faker = Faker()

    customers = []
    for i in range(amount):

        if i % ROWS_PER_BATCH == 0:
            print(f"Generated {i} rows")

        first_name = faker.first_name()
        last_name = faker.last_name()
        age = faker.random_int(18, 60)
        email = faker.email()
        phone_number = faker.phone_number()

        customers.append(Customer(first_name, last_name, age, email, phone_number))

    return customers


def generate_sql(customers):

    with open("customers.sql", "w") as file:
        file.write("TRUNCATE TABLE Customer RESTART IDENTITY CASCADE;")

    sql = "INSERT INTO Customer (first_name, last_name, age, email, phone_number) VALUES"
    i = 0
    for customer in customers:
        sql += f"('{customer.first_name}', '{customer.last_name}', '{customer.age}', '{customer.email}', '{customer.phone_number}'),"

        if i % ROWS_PER_BATCH == 0:
            # write the sql to a file

            with open("customers.sql", "a") as file:
                file.write(sql[:-1] + ";")

            print(f"Written {i} rows to file")
            sql = "INSERT INTO Customer (first_name, last_name, age, email, phone_number) VALUES "

        i += 1

    if sql != "INSERT INTO Customer (first_name, last_name, age, email, phone_number) VALUES ":
        with open("customers.sql", "a") as file:
            file.write(sql[:-1] + ";")
        print(f"Written {i} rows to file - last batch")

    print("Done! :")


if __name__ == '__main__':
    customers = generate_customers(ROWS_TO_GENERATE)
    generate_sql(customers)