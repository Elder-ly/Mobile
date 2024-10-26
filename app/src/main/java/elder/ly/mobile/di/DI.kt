package elder.ly.mobile.di

import elder.ly.mobile.repository.calendar.CalendarRepository
import elder.ly.mobile.repository.message.MessageRepository
import elder.ly.mobile.repository.proposal.ProposalRepository
import elder.ly.mobile.repository.specialtie.SpecialtieRepository
import elder.ly.mobile.repository.user.UserRepository
import elder.ly.mobile.service.CalendarService
import elder.ly.mobile.service.MessageService
import elder.ly.mobile.service.ProposalService
import elder.ly.mobile.service.SpecialtieService
import elder.ly.mobile.service.UserService
import elder.ly.mobile.utils.Rest
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

    single<MessageRepository> {
        MessageRepository(get())
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
}