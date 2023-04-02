import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Button,
  Checkbox,
  FormControlLabel,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@material-ui/core";

export default function App() {
  const [cars, setCars] = useState([]);
  const [newCar, setNewCar] = useState({
    make: "",
    model: "",
    year: "",
    price: "",
    isElectric: false,
  });
  const [filterYear, setFilterYear] = useState("");
  const [editCar, setEditCar] = useState(null);

  useEffect(() => {
    getCars();
  }, []);

  const getCars = () => {
    axios
      .get("http://localhost:8080/cars")
      .then((response) => setCars(response.data))
      .catch((error) => console.log(error));
  };

  const handleAddCarChange = (event) => {
    const target = event.target;
    const value = target.type === "checkbox" ? target.checked : target.value;
    const name = target.name;

    setNewCar((prevCar) => ({ ...prevCar, [name]: value }));
  };

  const handleAddCarSubmit = (event) => {
    event.preventDefault();

    if (editCar) {
      axios
        .put(`http://localhost:8080/cars/${editCar.id}`, newCar)
        .then(() => {
          setEditCar(null);
          setNewCar({
            make: "",
            model: "",
            year: "",
            price: "",
            isElectric: false,
          });
          getCars();
        })
        .catch((error) => console.log(error));
    } else {
      axios
        .post("http://localhost:8080/cars", newCar)
        .then(() => {
          setNewCar({
            make: "",
            model: "",
            year: "",
            price: "",
            isElectric: false,
          });
          getCars();
        })
        .catch((error) => console.log(error));
    }
  };

  const handleDeleteCar = (id) => {
    axios
      .delete(`http://localhost:8080/cars/${id}`)
      .then(() => getCars())
      .catch((error) => console.log(error));
  };

  const handleEditCar = (car) => {
    setEditCar(car);
    setNewCar(car);
  };

  const handleFilterCarsSubmit = (event) => {
    event.preventDefault();

    axios
      .get(`http://localhost:8080/cars/year/${filterYear}`)
      .then((response) => setCars(response.data))
      .catch((error) => console.log(error));
  };

  return (
    <div>
      {/* Add Car Form */}
      <h2>{editCar ? "Edit Car" : "Add Car"}</h2>
      <form onSubmit={handleAddCarSubmit}>
        <TextField
          label="Make"
          name="make"
          required
          onChange={handleAddCarChange}
          value={newCar.make}
        />
        <TextField
          label="Model"
          name="model"
          required
          onChange={handleAddCarChange}
          value={newCar.model}
        />
        <TextField
          label="Year"
          name="year"
          type="number"
          required
          onChange={handleAddCarChange}
          value={newCar.year}
        />
        <TextField
          label="Price"
          name="price"
          type="number"
          required
          onChange={handleAddCarChange}
          value={newCar.price}
          />
          <FormControlLabel
          control={
          <Checkbox
                     name="isElectric"
                     checked={newCar.isElectric}
                     onChange={handleAddCarChange}
                   />
          }
          label="Electric"
          />
          <Button variant="contained" color="primary" type="submit">
          {editCar ? "Save" : "Add"}
          </Button>
          </form>
          {/* Filter Cars Form */}
  <h2>Filter Cars</h2>
  <form onSubmit={handleFilterCarsSubmit}>
    <TextField
      label="Year"
      name="year"
      type="number"
      required
      onChange={(event) => setFilterYear(event.target.value)}
      value={filterYear}
    />
    <Button variant="contained" color="primary" type="submit">
      Filter
    </Button>
  </form>

  {/* Cars Table */}
  <h2>Cars</h2>
  <TableContainer component={Paper}>
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>Make</TableCell>
          <TableCell>Model</TableCell>
          <TableCell>Year</TableCell>
          <TableCell>Price</TableCell>
          <TableCell>Electric</TableCell>
          <TableCell>Edit</TableCell>
          <TableCell>Delete</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {cars.map((car) => (
          <TableRow key={car.id}>
            <TableCell>{car.make}</TableCell>
            <TableCell>{car.model}</TableCell>
            <TableCell>{car.year}</TableCell>
            <TableCell>{car.price}</TableCell>
            <TableCell>
              <Checkbox disabled checked={car.isElectric} />
            </TableCell>
            <TableCell>
              <Button
                variant="contained"
                color="primary"
                onClick={() => handleEditCar(car)}
              >
                Edit
              </Button>
            </TableCell>
            <TableCell>
              <Button
                variant="contained"
                color="secondary"
                onClick={() => handleDeleteCar(car.id)}
              >
                Delete
              </Button>
            </TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  </TableContainer>
</div>
);
}