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

const API_URL = "api"

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
  const [sortCriteria, setSortCriteria] = useState("make");

  useEffect(() => {
    getCars();
  }, []);

  const getCars = () => {
    axios
      .get("/api/cars/")
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
        .put(`/api/cars/${editCar.id}`, newCar)
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
        .post("/api/cars", newCar)
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
      .delete(`/api/cars/${id}`)
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
      .get(`/api/cars/year/${filterYear}`)
      .then((response) => setCars(response.data))
      .catch((error) => console.log(error));
  };

  const handleSortCriteriaChange = (name) => {
    setSortCriteria(name);
    console.log(sortCriteria)
  };

  return (
    <div>
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

  <h2>Cars</h2>
  <TableContainer component={Paper}>
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>
            </TableCell>
        </TableRow>
        <TableRow>
          <TableCell>
          <Button
                variant="contained"
                color="primary"
                size="small"
                name="make"
                onClick={() => handleSortCriteriaChange("make")}
          >
              Sort
          </Button>
            Make
          </TableCell>
          <TableCell>
            <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  name="model"
                  onClick={() => handleSortCriteriaChange("model")}
            >
                Sort
            </Button>
            Model
          </TableCell>
          <TableCell>
            <Button
                  variant="contained"
                  color="primary"
                  size="small"
                  name="year"
                  onClick={() => handleSortCriteriaChange("year")}
            >
                Sort
            </Button>
            Year
          </TableCell>
          <TableCell>
          <Button
                variant="contained"
                color="primary"
                size="small"
                name="make"
                onClick={() => handleSortCriteriaChange("price")}
          >
              Sort
          </Button>
            Price
          </TableCell>
          <TableCell>Electric</TableCell>
          <TableCell>Edit</TableCell>
          <TableCell>Delete</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {cars.sort((a, b) => a[sortCriteria] > b[sortCriteria] ? 1 : -1)
        .map((car) => (
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