import React, { useEffect, useState } from "react";
import axios from "axios";
import { useSearchParams } from "react-router-dom";
import "react-datepicker/dist/react-datepicker.css";
import Card from "./UI/Card";


const Checkout = props => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [departureFlightQuery, setDepartureFlightQuery] = useState(searchParams.get("departureflight"));
    const [returnFlightQuery, setReturnFlightQuery] = useState(searchParams.get("returnflight"));
    const [departureFlight, setDepartureFlight] = useState();
    const [returnFlight, setReturnFlight] = useState();

    const [passengerName, setPassengerName] = useState("Namee");
    const [passengerSurname, setPassengerSurname] = useState("Surnamee");
    const [passengerNationality, setPassengerNationality] = useState("Portuguese");
    const [passengerIdentification, setPassengerIdentification] = useState("123456789");
    const [passengerAge, setPassengerAge] = useState(">9");
    const [smallBags, setSmallBags] = useState(1);
    const [cabinBags, setCabinBags] = useState(0);
    const [holdBags, setHoldBags] = useState(0);

    const [departureCost, setDepartureCost] = useState(0);
    const [returnCost, setReturnCost] = useState(0);

    useEffect(() => {
        getFlights("departure");
        if (returnFlightQuery)
            getFlights("return");
    },[]);

    const getFlights = (type) => {
        axios.get(props.api_url+"/flights/"+(type==="departure"?departureFlightQuery:returnFlightQuery)).then((response) => {
            if (response.status === 200) {
                if (type==="departure") {
                    setDepartureFlight(response.data);
                    setDepartureCost(response.data.price);
                } else {
                    setReturnFlight(response.data);
                    setReturnCost(response.data.price);
                }
            }
        }).catch((err) => {
            console.error("error: " + err);
        });
    }

    const ageChangeHandler = (choice) => {
        switch (choice.target.value) {
            case "between 2 and 9 years":
                setPassengerAge("bet2and9");
                break;
            case "< 2 years":
                setPassengerAge("<2");
                break;
            default:
                setPassengerAge(">9");
                break;
        }
    }

    useEffect(() => {//cost related things
        if (departureFlight) {
            let dCost = departureFlight.price + (smallBags*departureFlight.airline.smallBagPrice) + (cabinBags*departureFlight.airline.cabinBagPrice) + (holdBags*departureFlight.airline.holdBagPrice);
            dCost += passengerAge==='bet2and9'? 1 : passengerAge==='<2'? departureFlight.airline.ageLessThan2Price : departureFlight.airline.ageMoreThan9Price;
            setDepartureCost(dCost.toFixed(2));
            if (returnFlight) {
                let rCost = returnFlight.price + (smallBags*returnFlight.airline.smallBagPrice) + (cabinBags*returnFlight.airline.cabinBagPrice) + (holdBags*returnFlight.airline.holdBagPrice);
                dCost += passengerAge==='bet2and9'? 1 : passengerAge==='<2'? returnFlight.airline.ageLessThan2Price : returnFlight.airline.ageMoreThan9Price;
                setReturnCost(rCost.toFixed(2));
            }
        }
    },[smallBags,cabinBags,holdBags,passengerAge,departureFlight,returnFlight]);

    const handleBagChange = (el) => {
        switch (el.target.name) {
            case 'cabin':
                setCabinBags(el.target.value);
                break;
            case 'hold':
                setHoldBags(el.target.value);
                break;
            default:
                setSmallBags(el.target.value);
                break;
        }
    }

    const handleBook = async () => {
        let success = true;

        let ticketDeparture = {
            passenger: {
                name: passengerName,
                surname: passengerSurname,
                nationality: passengerNationality,
                passport: passengerIdentification,
                age: passengerAge
            },
            bags: {
              small: {quantity: smallBags, price: departureFlight.airline.smallBagPrice},
              cabin: {quantity: cabinBags, price: departureFlight.airline.cabinBagPrice},
              hold: {quantity: holdBags, price: departureFlight.airline.holdBagPrice}
            },
            price: parseFloat(departureCost).toFixed(2),
            flightId: departureFlight.id
        }
        await axios.post(props.api_url+"/tickets/book", ticketDeparture).then((response) => {
            console.log(response);
            if (response.status !== 200) {
                success = false;
            }
        }).catch((err) => {
            console.error("error: " + err);
        });

        if (returnFlight) {
            let ticketReturn = {
                passenger: {
                    name: passengerName,
                    surname: passengerSurname,
                    nationality: passengerNationality,
                    passport: passengerIdentification,
                    age: passengerAge
                },
                bags: {
                  small: {quantity: smallBags, price: returnFlight.airline.smallBagPrice},
                  cabin: {quantity: cabinBags, price: returnFlight.airline.cabinBagPrice},
                  hold: {quantity: holdBags, price: returnFlight.airline.holdBagPrice}
                },
                price: parseFloat(returnCost).toFixed(2),
                flightId: returnFlight.id
            }
            await axios.post(props.api_url+"/tickets/book", ticketReturn).then((response) => {
                console.log(response);
                if (response.status !== 200) {
                    success = false;
                }
            }).catch((err) => {
                console.error("error: " + err);
            });
        }
        alert(success ? "success" : "failed")
    };

    return (
        <>
            <div style={{width:"50%",marginLeft:"auto",marginRight:"auto",marginTop:"5vh"}}>
                <p>Passenger Details</p>
                <label>Name: </label><br/>
                <input type="text" value={passengerName} onChange={(value) => setPassengerName(value.target.value)} placeholder="Name" /><br/>
                <label>Surname: </label><br/>
                <input type="text" value={passengerSurname} onChange={(value) => setPassengerSurname(value.target.value)} placeholder="Surname" /><br/>
                <label>Nationality: </label><br/>
                <input type="text" value={passengerNationality} onChange={(value) => setPassengerNationality(value.target.value)} placeholder="Nationality" /><br/>
                <label>NIF/Passport: </label><br/>
                <input type="text" value={passengerIdentification} onChange={(value) => setPassengerIdentification(value.target.value)} placeholder="NIF/Passport" /><br/>
                <label>Age: </label><br/>
                <select onChange={(choice) => ageChangeHandler(choice)}>
                    <option>{"< 2 years"}</option>
                    <option>between 2 and 9 years</option>
                    <option selected>{"> 9 years"}</option>
                </select>

                <br/><br/>
                <p>Bags</p>
                <span>Small bags </span><input type="number" min="0" name="small" value={smallBags} onChange={(el) => handleBagChange(el)} disabled/><br/>
                <span>Cabin bags </span><input type="number" min="0" name="cabin" value={cabinBags} onChange={(el) => handleBagChange(el)}/><br/>
                <span>Hold bags </span><input type="number" min="0" name="hold" value={holdBags} onChange={(el) => handleBagChange(el)} /><br/>
                <br/><br/>
                <br/><br/>
                
                <p>Departure</p>
                {departureFlight &&
                    <>
                    <Card>
                        <span style={{position: "absolute", top: "5px", right: "5px"}}>{departureFlight.airline.name}</span>
                        <span>From {departureFlight.origin.name} to {departureFlight.destination.name}</span><br/><br/>
                        <span>Departure: {(new Date(departureFlight.departure)).toLocaleString("pt")}</span><br/>
                        <span>Arrival: {(new Date(departureFlight.arrival)).toLocaleString("pt")}</span><br/>
                        <span>Duration: {Math.floor(departureFlight.flightDurationMinutes/60)}h{departureFlight.flightDurationMinutes%60}min</span><br/>
                        <span style={{position: "absolute", bottom: "5px", right: "5px"}}>{departureCost} €</span>
                    </Card>
                    </>
                }
                
                {returnFlight &&
                    <>
                    <p>Return</p>
                    <Card>
                        <span style={{position: "absolute", top: "5px", right: "5px"}}>{returnFlight.airline.name}</span>
                        <span>From {returnFlight.origin.name} to {returnFlight.destination.name}</span><br/><br/>
                        <span>Departure: {(new Date(returnFlight.departure)).toLocaleString("pt")}</span><br/>
                        <span>Arrival: {(new Date(returnFlight.arrival)).toLocaleString("pt")}</span><br/>
                        <span>Duration: {Math.floor(returnFlight.flightDurationMinutes/60)}h{returnFlight.flightDurationMinutes%60}min</span><br/>
                        <span style={{position: "absolute", bottom: "5px", right: "5px"}}>{returnCost} €</span>
                    </Card>
                    </>
                }

                <p>TOTAL: {(parseFloat(departureCost)+parseFloat(returnCost)).toFixed(2)}€  <button onClick={handleBook}>Book</button></p>
            </div>
        </>
    );
};

export default Checkout;