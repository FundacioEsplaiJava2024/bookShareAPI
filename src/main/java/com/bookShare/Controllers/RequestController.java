@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request, Principal principal) {
        Request newRequest = requestService.createRequest(request, principal.getName());
        return ResponseEntity.ok(newRequest);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Request>> getRequestsByBookId(@PathVariable Long bookId) {
        List<Request> requests = requestService.getRequestsByBookId(bookId);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Request>> getRequestsByUserId(@PathVariable Long userId) {
        List<Request> requests = requestService.getRequestsByUserId(userId);
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable Long requestId, @RequestBody String status) {
        Request updatedRequest = requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
