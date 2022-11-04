import React, { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import axios from "axios";
import Card from "./UI/Card";
import Select from 'react-select';
import { Link, useNavigate } from "react-router-dom";

const Search = props => {
    const [searchParams, setSearchParams] = useSearchParams();
    const [origin, setOrigin] = useState(searchParams.get("origin"));
    const [destination, setDestination] = useState(searchParams.get("destination"));
    const [departureDate, setDepartureDate] = useState(searchParams.get("departure"));
    const [returnDate, setReturnDate] = useState(searchParams.get("return"));
    const [departureFlight, setDepartureFlight] = useState(searchParams.get("departureflight"));

    const [departures, setDepartures] = useState([]);
    const [optionsAirlines, setOptionsAirlines] = useState([]);
    const [selectedAirlines, setSelectedAirlines] = useState([]);
    const [departureTimeStart, setDepartureTimeStart] = useState(0);
    const [departureTimeEnd, setDepartureTimeEnd] = useState(24);

    const [airlinesQuery, setAirlinesQuery] = useState(null);
    const [departureTimesQuery, setDepartureTimesQuery] = useState(null);

    const navigate = useNavigate();
    
    useEffect(() => {
        search();
        axios.get(props.api_url+"/airlines").then((response) => {
            if (response.status === 200) {
                let ops = [];
                response.data.map( el => ops.push({value: el.id, label: el.name}))
                setOptionsAirlines(ops);
                setSelectedAirlines(ops);
            }
        }).catch((err) => {
            console.error("error: " + err);
        });
    },[airlinesQuery,departureTimesQuery,navigate]);
    
    const search = () => {
        let params = {
            departure: departureFlight ? returnDate : departureDate,
            origin: departureFlight ? destination : origin,
            destination: departureFlight ? origin : destination
        }
        if(airlinesQuery)
            params.airlines = airlinesQuery;

        if(departureTimesQuery)
            params.departuretimes = departureTimesQuery;

        axios.get(props.api_url+"/flights/search", {params: params}).then((response) => {
            if (response.status === 200) {
                setDepartures(response.data);
            }
        }).catch((err) => {
            console.error("error: " + err);
        });
    };

    const airLinesChangeHandler = (choices) => {
        setSelectedAirlines(choices);
        let query = "";
        for (let i = 0; i < choices.length; i++) {
            const choice = choices[i];
            query += choice.label + (choices[i+1]?"-":"");
        }
        setAirlinesQuery(query);
    };

    const handleDepartureTimesChange = (value) => {
        let query = "";
        if (value.target.name === 'start') {
            setDepartureTimeStart(value.target.value);
            query += value.target.value+"-"+departureTimeEnd
        } else {
            setDepartureTimeEnd(value.target.value);
            query += departureTimeStart+"-"+value.target.value
        }
        setDepartureTimesQuery(query);
    };

    const handleFlightClick = (element) => {
        let selectedFlightId = element.target.getAttribute("data-key");
        if (returnDate !== 'null' && !departureFlight) {//round trip
            navigate(`/search?origin=${origin}&destination=${destination}&departure=${departureDate}&return=${returnDate}&departureflight=${selectedFlightId}`);
            window.location.reload(false);
        } else if(departureFlight) {
            navigate(`/checkout?departureflight=${departureFlight}&returnflight=${selectedFlightId}`);
        } else {//one way
            navigate(`/checkout?departureflight=${selectedFlightId}`);
        }
    }

    return (
        <>
            <div style={{width:"50%",marginLeft:"auto",marginRight:"auto",marginTop:"5vh"}}>
                <label>Airlines</label>
                <div style={{width:"300px"}}><Select options={optionsAirlines} value={selectedAirlines} onChange={(choices) => airLinesChangeHandler(choices)} isMulti /></div>
                <label>{departureFlight? "Return" : "Departure"} times</label><br/>
                <input type="number" min="0" max="23" name="start" value={departureTimeStart} onChange={(value) => handleDepartureTimesChange(value)}/> to <input type="number" min="1" max="24" name="end" value={departureTimeEnd} onChange={(value) => handleDepartureTimesChange(value)}/>
                
                <h3>{departureFlight? "Returns" : "Departures"}</h3>
                {departures.map((flight) => (
                    <Card customClickEvent={(element) => handleFlightClick(element)} key={flight.id} dataKey={flight.id} >
                        <span style={{position: "absolute", top: "5px", right: "5px"}}>{flight.airline.name}</span>
                        <span>From {flight.origin.name} to {flight.destination.name}</span><br/><br/>
                        <span>Departure: {(new Date(flight.departure)).toLocaleString("pt")}</span><br/>
                        <span>Arrival: {(new Date(flight.arrival)).toLocaleString("pt")}</span><br/>
                        <span>Duration: {Math.floor(flight.flightDurationMinutes/60)}h{flight.flightDurationMinutes%60}min</span><br/>
                        <span style={{position: "absolute", bottom: "5px", right: "5px"}}>{flight.price} €</span>
                    </Card>
                ))}
            </div>
            
        </>
    );
};

export default Search;