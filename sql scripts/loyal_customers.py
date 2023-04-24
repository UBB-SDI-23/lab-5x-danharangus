ROWS_TO_GENERATE = 100000
DATA_FOR_ROW_TO_GENERATE = 100
ROWS_PER_BATCH = 1000
AMOUNT_OF_FILES = 1

from faker import Faker


class LoyalCustomer:
    def __init__(self, dealership_id, customer_id, loyalty_points, registration_year):
        self.dealership_id = dealership_id
        self.customer_id = customer_id
        self.loyalty_points = loyalty_points
        self.registration_year = registration_year

    def __str__(self):
        return f'{self.dealership_id}, {self.customer_id}, {self.loyalty_points}, {self.registration_year}'


def generate():
    faker: Faker = Faker()

    data = []
    for i in range(ROWS_TO_GENERATE):

        if i % ROWS_PER_BATCH == 0:
            print(f"Generated {i * DATA_FOR_ROW_TO_GENERATE} rows")

        customer_ids = []
        for _ in range(DATA_FOR_ROW_TO_GENERATE):
            dealership_id = i + 1
            customer_id = faker.random_int(1, 10000000)

            while customer_id in customer_ids:
                customer_id = faker.random_int(1, 1000000)
            customer_ids.append(customer_id)

            registration_year = faker.random_int(2000, 2020)
            loyalty_points = faker.random_int(0, 1000)

            data.append(LoyalCustomer(dealership_id, customer_id, loyalty_points, registration_year))

    return data


def generate_sql(data):
    with open("loyal_customers0.sql", "w") as file:
        file.write("TRUNCATE TABLE loyal_customers RESTART IDENTITY CASCADE;")

    sql = "INSERT INTO loyal_customers (car_dealership_id, customer_id, loyalty_points, registration_year) VALUES "
    i = 0
    for entity in data:
        i += 1

        sql += f"({entity.dealership_id}, {entity.customer_id}, '{entity.loyalty_points}', {entity.registration_year}), "
        if i % (ROWS_PER_BATCH * DATA_FOR_ROW_TO_GENERATE) == 0:
            with open(f"loyal_customers0.sql", "a") as file:
                file.write(sql[:-2] + ";")

            print(f"Written {i} rows to file")
            sql = "INSERT INTO loyal_customers (car_dealership_id, customer_id, loyalty_points, registration_year) VALUES "

    if sql != "INSERT INTO loyal_customers (car_dealership_id, customer_id, loyalty_points, registration_year) VALUES ":
        with open(f"loyal_customers0.sql", "a") as file:
            file.write(sql[:-2] + ";")
        print(f"Written {i} rows to file - last batch")

    print("Done! :")


if __name__ == '__main__':
    data = generate()
    generate_sql(data)
