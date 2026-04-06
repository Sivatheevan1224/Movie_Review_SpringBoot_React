import React from 'react'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faVideoSlash} from '@fortawesome/free-solid-svg-icons'
import { Container, Button, Nav, Navbar } from 'react-bootstrap'
import { NavLink } from 'react-router-dom'


const Header = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
        <Container fluid>
            <Navbar.Brand href="/" className="menu" style={{"color":"gold"}}>
                <FontAwesomeIcon icon={faVideoSlash} /> Siva's Movie Gold
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="navbarScroll" />
            <Navbar.Collapse id="navbarScroll">
                <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
                    <NavLink to="/" className="nav-link">Home</NavLink>
                    <NavLink to="/watchList" className="nav-link">Watch List</NavLink>
                </Nav>
                <Button variant="outline-info" className="me-2">Login</Button>
                <Button variant="outline-info">Register</Button>
            </Navbar.Collapse>
        </Container>

    </Navbar>
  )
}

export default Header