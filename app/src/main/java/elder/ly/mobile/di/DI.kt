package elder.ly.mobile.di

import elder.ly.mobile.data.repository.calendar.CalendarRepository
import elder.ly.mobile.data.repository.message.MessageRepository
import elder.ly.mobile.data.repository.proposal.ProposalRepository
import elder.ly.mobile.data.repository.specialtie.SpecialtieRepository
import elder.ly.mobile.data.repository.user.UserRepository
import elder.ly.mobile.domain.service.CalendarService
import elder.ly.mobile.domain.service.MessageService
import elder.ly.mobile.domain.service.ProposalService
import elder.ly.mobile.domain.service.SpecialtieService
import elder.ly.mobile.domain.service.UserService
import elder.ly.mobile.data.Rest
import elder.ly.mobile.data.repository.message.IMessageRepository
import elder.ly.mobile.data.repository.message.MessageRepositoryLocal
import elder.ly.mobile.ui.viewmodel.ChatListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<CalendarService> {
        Rest.calendarService
    }

    single<CalendarRepository> {
        CalendarRepository(get())
    }

    single<MessageService> {
        Rest.messageService
    }

    single<IMessageRepository> {
        // MessageRepository(get())
        MessageRepositoryLocal(get())
    }

    single<ProposalService> {
        Rest.proposalService
    }

    single<ProposalRepository> {
       ProposalRepository(get())
    }

    single<SpecialtieService> {
        Rest.specialtieService
    }

    single<SpecialtieRepository> {
        SpecialtieRepository(get())
    }

    single<UserService> {
        Rest.userService
    }

    single<UserRepository> {
        UserRepository(get())
    }

    viewModel {
        ChatListViewModel(get())
    }
}