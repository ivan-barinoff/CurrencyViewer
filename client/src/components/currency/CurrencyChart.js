import React, {Component} from 'react'

import {LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend} from 'recharts'

class CurrencyChart extends Component {

    render() {
        const data = this.props.data
        return (
            <LineChart width={700} height={300} data={data.records} margin={{top: 25, right: 30, left: 20, bottom: 5}}>
                <XAxis dataKey="date"/>
                <YAxis/>
                <CartesianGrid strokeDasharray="3 3"/>
                <Tooltip/>
                <Legend/>
                <Line type="monotone" dataKey="usd" stroke="#8884d8" activeDot={{r: 8}}/>
                <Line type="monotone" dataKey="eur" stroke="#82ca9d"/>
            </LineChart>
        )
    }
}

export default CurrencyChart
