import React, {Component} from 'react'
import {Table} from 'react-bootstrap'
import CurrencyTableItem from './CurrencyTableItem'

class CurrencyTable extends Component {

    render() {
        const data = this.props.data
        const records = data.records

        return (
            <Table striped bordered condensed hover>
                <thead>
                <tr>
                    <th>Date</th>
                    <th>$</th>
                    <th>€</th>
                </tr>
                </thead>
                <tbody>
                {
                    records ? records.map(function (record) {
                        return <CurrencyTableItem record={record} properties={data.properties}/>
                    }) : null
                }
                </tbody>
            </Table>
        )
    }

}

export default CurrencyTable
