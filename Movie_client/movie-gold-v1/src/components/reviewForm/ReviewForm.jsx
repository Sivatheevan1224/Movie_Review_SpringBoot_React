import React from 'react'
import {Form, Button} from 'react-bootstrap'

const ReviewForm = ({ handleSubmit, revText, labelText }) => {
  return (
    <Form>
      <Form.Group className='mb-3' controlId="exampleForm.ControlTextarea1">
        <Form.Label>{labelText}</Form.Label>
        <Form.Control 
          as="textarea" 
          rows={3} 
          placeholder="Share your thoughts about the movie..." 
          ref={revText}
        />
      </Form.Group>
      <Button variant="outline-info" type="submit" onClick={handleSubmit}>
        Submit
      </Button>
    </Form>
  )
}

export default ReviewForm