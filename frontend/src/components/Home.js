import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Select from 'react-select';
import axios from "axios";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import Card from "./UI/Card";

const Home = props => {
    const [optionsOrigin, setOptionsOrigin] = useState([]);
    const [optionsDestination, setOptionsDestination] = useState([]);
    const [origin, setOrigin] = useState();
    const [destination, setDestination] = useState();
    const [isOneWay, setIsOneWay] = useState();
    const [departureDate, setDepartureDate] = useState();
    const [returnDate, setReturnDate] = useState();
    const todayDate = new Date();

    useEffect(() => {
        axios.get(props.api_url+"/airports").then((response) => {
            if (response.status === 200) {
                let ops = [];
                response.data.map( el => ops.push({value: el.id, label: el.name}))
                setOptionsOrigin(ops);
            }
        }).catch((err) => {
            console.error("error: " + err);
        });

    },[]);
    
    const originsChangeHandler = (choice) => {
        setOrigin(choice.value);

        axios.get(`${props.api_url}/airports/${choice.value}/destinations`).then((response) => {
            if (response.status === 200) {
                let ops = [];
                response.data.map( el => ops.push({value: el.id, label: el.name}))
                setOptionsDestination(ops);
            }
        }).catch((err) => {
            console.error("error: " + err);
        });
    }

    const destinationsChangeHandler = (choice) => {
        setDestination(choice.value);
    }
    
    const tripTypeChangeHandler = (choice) => {
        let is = choice.target.id === "oneWay" ? true : false;
        setIsOneWay(is);
        setDepartureDate(new Date());
        if(!is)
            setReturnDate(new Date());
        else
            setReturnDate(null);
    }



    return (
        <>
        <div style={{width:"50%",marginLeft:"auto",marginRight:"auto",marginTop:"5vh"}}>
            <label>Origin</label>
            <Select options={optionsOrigin} onChange={(choice) => originsChangeHandler(choice)} />
            <br/>
            <label>Destination</label>
            {optionsDestination.length > 0?
            <>
                <Select options={optionsDestination} onChange={(choice) => destinationsChangeHandler(choice)} />
                <br/>
                {destination &&
                    <>
                    <label>Trip type</label>
                    <div>
                        <input type="radio" id="oneWay" name="tripType" onChange={(choice) => tripTypeChangeHandler(choice)} />
                        <label htmlFor="oneWay">One-way</label>
                    </div>
                    <div>
                        <input type="radio" id="roundTrip" name="tripType" onChange={(choice) => tripTypeChangeHandler(choice)} />
                        <label htmlFor="roundTrip">Round trip</label>
                    </div>
                    <br/>
                    {isOneWay != null &&
                        <>
                        <label>Dates</label><br/>
                        {isOneWay?
                        <>
                            departure: <DatePicker selected={departureDate} minDate={new Date()} onChange={(date) => setDepartureDate(date)} />
                        </>
                        :
                        <>
                            departure: <DatePicker selected={departureDate} onChange={(date) => setDepartureDate(date)} />
                            return: <DatePicker selected={returnDate} onChange={(date) => setReturnDate(date)} />
                        </>
                        }
                        <br/><br/>
                        <Link to={`/search?origin=${origin}&destination=${destination}&departure=${departureDate?departureDate.toLocaleDateString('fr-CA'):false}&return=${returnDate?returnDate.toLocaleDateString('fr-CA'):false}`}>Search</Link>
                        </>
                    }
                    </>
                }
            </>
            :
                <h4>No destinations for that origin.</h4>
            }
            </div>
        </>
    );
};

export default Home;