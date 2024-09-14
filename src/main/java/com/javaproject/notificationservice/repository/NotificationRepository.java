package com.javaproject.notificationservice.repository;

import com.javaproject.notificationservice.entity.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long>{
    Notification findAllById(long userId);

    // TODO - COLOCAR ISSO AQUI NA HORA DE CHAMAR O FIND:
    // Repositorio.findById(id)
    //        .orElseThrow(() -> new ObjetoNaoEncontradoException("SeuObjeto com id " + id + " não foi encontrado"));

    @Override
    default <S extends Notification> S save(S entity) {
        return null;
    }
}
