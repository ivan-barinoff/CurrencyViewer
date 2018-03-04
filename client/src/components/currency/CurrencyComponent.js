import React, {Component} from 'react'
import {Grid, Tabs, Tab, Row, Col, Alert} from 'react-bootstrap'

import CurrencyChart from './CurrencyChart'
import CurrencyTable from './CurrencyTable'
import Spinner from 'react-spinkit'
import {fetchRecords} from '../utils/CurrencyApi'

class CurrencyComponent extends Component {

    componentWillMount() {
        this.state = {
            data: null,
            isLoading: true,
            error: null
        }

        fetchRecords().then(json => this.setState({data: json.data, isLoading: false}))
                      .catch(error => this.setState({error: 'An error occurred during loading data', isLoading: false}))
    }

    render() {
        const data = this.state.data
        const isLoading = this.state.isLoading
        const error = this.state.error

        return (
            <div>
                <Grid>
                    <Row className="show-grid">
                        <Col md={8}>
                            <h2>
                                Currency Viewer
                            </h2>
                        </Col>
                    </Row>
                    {isLoading &&
                    <Row className="show-grid">
                        <Col xsOffset={4} md={4}>
                            <Spinner name="wave"/>
                        </Col>
                    </Row>
                    }
                    {(!isLoading && error) &&
                    <Row className="show-grid">
                        <Col md={8}>
                            <Alert bsStyle="danger">
                                {error}
                            </Alert>
                        </Col>
                    </Row>
                    }
                    {(!isLoading && data) &&
                    <Row className="show-grid">
                        <Col md={8}>
                            <Tabs defaultActiveKey={1} id="uncontrolled-tab-example">
                                <Tab eventKey={1} title="Month data">
                                    <CurrencyTable data={data}/>
                                </Tab>
                                <Tab eventKey={2} title="Chart">
                                    <CurrencyChart data={data}/>
                                </Tab>
                            </Tabs>
                        </Col>
                    </Row>
                    }
                </Grid>
            </div>
        )
    }
}

export default CurrencyComponent
