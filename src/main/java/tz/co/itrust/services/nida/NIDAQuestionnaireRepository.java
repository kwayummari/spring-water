package tz.co.itrust.services.nida;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import tz.co.itrust.services.nida.entities.NidaResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class NIDAQuestionnaireRepository implements JpaRepository<NidaResponseEntity, UUID> {
   public void flush() {
   }

   public <S extends NidaResponseEntity> S saveAndFlush(S entity) {
      return null;
   }

   public <S extends NidaResponseEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
      return List.of();
   }

   public void deleteAllInBatch(Iterable<NidaResponseEntity> entities) {
   }

   public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
   }

   public void deleteAllInBatch() {
   }

   /** @deprecated */
   public NidaResponseEntity getOne(UUID uuid) {
      return null;
   }

   /** @deprecated */
   public NidaResponseEntity getById(UUID uuid) {
      return null;
   }

   public NidaResponseEntity getReferenceById(UUID uuid) {
      return null;
   }

   public <S extends NidaResponseEntity> Optional<S> findOne(Example<S> example) {
      return Optional.empty();
   }

   public <S extends NidaResponseEntity> List<S> findAll(Example<S> example) {
      return List.of();
   }

   public <S extends NidaResponseEntity> List<S> findAll(Example<S> example, Sort sort) {
      return List.of();
   }

   public <S extends NidaResponseEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
      return null;
   }

   public <S extends NidaResponseEntity> long count(Example<S> example) {
      return 0L;
   }

   public <S extends NidaResponseEntity> boolean exists(Example<S> example) {
      return false;
   }

   public <S extends NidaResponseEntity, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
      return null;
   }

   public <S extends NidaResponseEntity> S save(S entity) {
      return null;
   }

   public <S extends NidaResponseEntity> List<S> saveAll(Iterable<S> entities) {
      return List.of();
   }

   public Optional<NidaResponseEntity> findById(UUID uuid) {
      return Optional.empty();
   }

   public boolean existsById(UUID uuid) {
      return false;
   }

   public List<NidaResponseEntity> findAll() {
      return List.of();
   }

   public List<NidaResponseEntity> findAllById(Iterable<UUID> uuids) {
      return List.of();
   }

   public long count() {
      return 0L;
   }

   public void deleteById(UUID uuid) {
   }

   public void delete(NidaResponseEntity entity) {
   }

   public void deleteAllById(Iterable<? extends UUID> uuids) {
   }

   public void deleteAll(Iterable<? extends NidaResponseEntity> entities) {
   }

   public void deleteAll() {
   }

   public List<NidaResponseEntity> findAll(Sort sort) {
      return List.of();
   }

   public Page<NidaResponseEntity> findAll(Pageable pageable) {
      return null;
   }
}
