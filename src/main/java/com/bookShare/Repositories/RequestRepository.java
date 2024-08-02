package com.bookShare.Repositories;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByBookId(Long bookId);
    List<Request> findByUserId(Long userId);
}
